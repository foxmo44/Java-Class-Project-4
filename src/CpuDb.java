import java.sql.*;
/**
 * <h1>Advanced Java - Project 4</h1>
 * <h1>CpuDb Class</h1>
 * This class works with the database table with the following columns.
 * The class provides functions to add a cpu list, retrieve a cpu list, and to clear the database
 * <p>
 * <b>Note:</b> The fields are: id, cpuname, performance, and price
 *
 * @author   Michael Fox
 * @version  Project 04
 * @since   2016.10.16
 */
public class CpuDb
{

    //SQL objects
    Connection c = null;
    boolean bConnected = false;

    /**
     * Default constructor
     */
    public CpuDb()
    {
        bConnected = Connect("cpudb", "tcc2016", "tcc2016");
    }

    /**
     * @param strTable - table for which the column names will be shown
     */
    public void ShowColumns(String strTable)
    {
        Statement s = null;
        ResultSet r = null;

        try
        {
            s = c.createStatement();

            r = s.executeQuery("SELECT * from " + strTable);

            ResultSetMetaData m = r.getMetaData();

            int col = m.getColumnCount();

            for (int i = 1; i <= col; i++)
            {
                System.out.printf("%s\t\t", m.getColumnName(i));
            }
            System.out.println();
        }
        catch ( SQLException e)
        {
            e.printStackTrace();
        }
    }


    /**
     * Connect to the database
     * @param strDatabase - database name
     * @param strUser - database user
     * @param strPassword - user password
     * @return true if the connection is made
     */
    private boolean Connect(String strDatabase, String strUser, String strPassword)
    {
        boolean bConnectStatus = false;

        try
        {
            //Had to suppress a SSL warning message that kept popping up
            c = DriverManager.getConnection("jdbc:mysql://localhost/" + strDatabase + "?autoReconnect=true&useSSL=false", strUser, strPassword);

            System.out.println("Database connection made\n");

            bConnectStatus = true;
        }
        catch ( SQLException e)
        {
            e.printStackTrace();
        }

        return(bConnectStatus);
    }

    /**
     * Provide the insert into the database of the CPU list
     * @param lstCpu - list of CPUs to insert
     * @return true if the setting of the db via inserts succeeded
     */
    public boolean SetCpuList(CPUList lstCpu)
    {
        boolean retValue = false;
        Statement s = null;
        String  strSql;
        ResultSet r = null;

        try
        {
            s = c.createStatement();

            //"insert into cputable( cpuname, performance, price) values( 'CPU1', 123, 55.66)"
            for(CPU objCPU : lstCpu.theList)
            {
                //Only insert if valid
                if(objCPU.getValid() == true)
                {
                    //Create the CPU
                    strSql = "insert into cputable( cpuname, performance, price) values('"
                            + objCPU.getCpuName() + "',"
                            + objCPU.getPerformance() + ","
                            + objCPU.getPrice() + ")";

                    //System.out.println(strSql);
                    s.execute(strSql);
                }
            }

        }
        catch ( SQLException e)
        {
            e.printStackTrace();
        }

        return (retValue);
    }


    /**
     * Get the CPU list from the the database and return the list
     * @param lstCpu - list of CPUs in the database
     * @return indicates if the loading of the cpu list from the database succeeded or not
     */
    public boolean GetCpuList(CPUList lstCpu)
    {
        boolean         retValue = false;
        Statement       s = null;
        ResultSet       r = null;

        try
        {
            //Get the statement object connected to the database
            s = c.createStatement();

            //Get all of the fields from the cpu table
            r = s.executeQuery("SELECT * from cputable order by price DESC");

            //Get the results set for the query
            ResultSetMetaData m = r.getMetaData();

            //Iterate through the record set and add to the CPU list
            while( r.next() ){
                lstCpu.AddCpu(new CPU(r.getString( "cpuname" ),r.getInt( "performance" ),r.getFloat( "price" )));
            }


//            //How many columns do we have
//            int col = m.getColumnCount();
//
//            //Show all of the data in the results set
//            while( r.next() ){
//                for( int i = 1; i <= col; i++ ){
//                    int t = m.getColumnType( i );
//                    switch( t ){
//                        case Types.INTEGER:
//                            System.out.print( r.getInt( i ) );
//                            break;
//                        case Types.VARCHAR:
//                            System.out.print( r.getString( i ) );
//                            break;
//                        case Types.DATE:
//                            System.out.print( r.getDate( i ) );
//                            break;
//                        case Types.FLOAT:
//                            System.out.print( r.getFloat( i ) );
//                            break;
//                        default:
//                            System.out.print("Unk("+ r.getType() + ")");
//                            break;
//                    }
//                    System.out.print( ";" );
//                }
//                System.out.println();
//            }

            retValue = true;
        }
        catch ( SQLException e)
        {
            e.printStackTrace();
        }

        return (retValue);
    }

    /**
     * Clear the data out of the database
     * @return if the table was cleared or not
     */
    public boolean Clear()
    {
        boolean     retValue = false;
        Statement   s = null;
        ResultSet   r = null;

        try
        {
            //Get the statement object connected to the database
            s = c.createStatement();

            //Get all of the fields from the cpu table
            s.execute("delete from cputable");

        }
        catch ( SQLException e)
        {
            e.printStackTrace();
        }

        return (retValue);
    }
}
