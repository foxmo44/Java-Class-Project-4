import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
/**
 * <h1>Advanced Java - Project 4</h1>
 * <h1>Project04Fox Class</h1>
 * This the runable class that calls a stream and lambda function to fill the CPUList.
 * The CPUDb class encapsulates the database interface for mysql.  It will take the CPUList
 * objects and populate a table of CPUs that ahs the name, performance and price.
 * <p>
 * <b>Note:</b> The fields are: id, cpuname, performance, and price
 *
 * @author   Michael Fox
 * @version  Project 04
 * @since   2016.10.16
 */
public class Project04Fox
{
    /**
     * This the main function that runs at the start
     * @param args - input arguments from the command line
     */
    static public void main(String[] args)
    {
        CPUList cpuList = new CPUList();            //The CPUList used to retrieve data from the fiile and store in the db
        CPUList cpuListRetrieved = new CPUList();   //The CPUList used to retrieve data from the database
        CpuDb   cpuDb = new CpuDb();                //The database object used to move data to and from the CPU Lists

        try
        {

            //Read in the file and store each line into the CPU objects in a list
            Files.lines(Paths.get("Project04Data.csv"))
                    .map(line -> line.split("\r\n")) // Stream<String[]>
                    .flatMap(Arrays::stream) // Stream<String>
                    .forEach(line -> cpuList.AddCpu(line));

            //Clear the list table for the new listing
            cpuDb.Clear();

            //Insert the Cpu List into the database
            cpuDb.SetCpuList(cpuList);

            //Retrieve the Cpu List into a different CPU List object from the database
            cpuDb.GetCpuList(cpuListRetrieved);

            //Show the report from the new list that was retrieved from the database
            cpuListRetrieved.ShowReport();

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
