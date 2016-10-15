// Author: Michael Fox
// Session: Advanced Java
// Project: Project 4
// Current Date: 9/25/2016
// DateDue: 2016.10.12

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

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
