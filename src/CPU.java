// Author: Michael Fox
// Session: Advanced Java
// Project: Project 4
// Current Date: 10/3/2016
// DateDue: 2016.10.12

//Regular expression input
// ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)" will pick out the commas only



//Class to store the information about the CPU
public class CPU
{
    private boolean m_bValid;
    private String m_strCPULine;
    private String m_strCPUName;
    private double m_dPerformance;
    private double m_dPrice;
    private double m_dValue; //Performance / Price
    private static final double NA_VALUE = 9999.99;


    /**
     * @param strCPULine is the CPU line from the file
     */
    public CPU(String strCPULine)
    {
        m_strCPULine = strCPULine;
        m_bValid = ParseCPULine(strCPULine);
    }

    /**
     * @param m_strCPULine - the input file line to be parsed
     */
    private boolean ParseCPULine(String m_strCPULine)
    {
        boolean bRetValue = true;
        String  strTemp;
        String strNumeric;

        //Use a regular expression to parse the line into the individual members

        //TODO - Figure out why regex not working for 1,509.00
        //messes up on the comma in the value
        String[] tokens = m_strCPULine.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");  //http://rubular.com/ is helpful for regex

        //Do we have the correct number of tokens from the split.  If not then mark as invalid
        if(tokens.length != 3)
        {
            bRetValue = false;
        }

        m_strCPUName = tokens[0];

        //Get the performance
        try
        {
            strTemp = tokens[1];
            strNumeric = strTemp.replaceAll("[^0-9.]+", "");  //Get rid of non digit characters
            m_dPerformance = Double.parseDouble(strNumeric);
        }
        catch(NumberFormatException ex)
        {
            m_dPerformance = NA_VALUE;
            bRetValue = false;
        }

        //Get the price
        try
        {
            strTemp = tokens[2];
            strNumeric = strTemp.replaceAll("[^0-9.]+", "");   //Get rid of non digit characters
            m_dPrice = Double.parseDouble(strNumeric);
        }
        catch(NumberFormatException ex)
        {
            m_dPrice = NA_VALUE; //Bogus Value
            bRetValue = false;
        }

        //If we have valid Performance and Price values then return the calculated value otherwise set to zero
        if(bRetValue)
        {
            m_dValue = m_dPerformance / m_dPrice;
        }
        else
        {
            m_dValue = 0.0;
        }

//        for(String strTemp1 : tokens)
//        {
//            System.out.printf("%s\t", strTemp1);
//        }
//
//        System.out.printf("\n");

        return bRetValue;
    }

    /**
     * @return  A string representing the CPU
     */
    public String toString()
    {
        return String.format("[%b]\t%s\t\t[%5.2f]\t[%5.2f]\n", m_bValid, m_strCPUName, m_dPerformance, m_dPrice);
        //return String.format("%s\n", m_strCPULine); //Just return the original line
    }

    /**
     * Getter
     * @return the performance as an double
     */
    public double getPerformance(){return(m_dPerformance);};


    /**
     * Getter
     * @return the price as a double
     */
    public double getPrice(){return(m_dPrice);};

    /**
     * Getter
     * @return the value as a double
     */
    public double getValue(){return(m_dValue);};


    /**
     * Getter
     * @return the CPU Name
     */
    public String getCpuName(){return(m_strCPUName);};

    /**
     * @return if the cpu's line was parsed with all valid values for the fields or not
     */
    public boolean getValid(){return(m_bValid);};
}
