// Author: Michael Fox
// Session: Advanced Java
// Project: Project 4
// Current Date: 10/3/2016
// DateDue: 2016.10.12

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;


public class CPUList
{
    ArrayList<CPU> theList = new ArrayList<>();

    /**
     * Default constructor for the CPU
     */
    public void CPUList()
    {

    }

    /**
     * @param strInputLine Input line to be used in creating the CPU object
     */
    public void AddCpu(String strInputLine)
    {
        theList.add(new CPU(strInputLine));
    }

    /**
     * @param tempCPU - A CPU object to add to the list
     */
    public void AddCpu(CPU tempCPU)
    {
        theList.add(tempCPU);
    }

    /**
     * @return the combined string showing the CPU list
     */
    public String toString()
    {
        String strString = "";

        for (CPU cpu : theList)
        {
            strString += cpu;
        }

        return(strString);
    }

    /**
     *  Print the CPU List Statistics
     *        average price of CPU's
     *        highest priced CPU
     *        lowest priced CPU
     *        best value CPU (performance / price )*
     */
    public void  PrintStatistics()
    {
        System.out.printf("average price CPU: %5.2f\n", theList.stream().mapToDouble(CPU::getPrice).average().getAsDouble());
        System.out.printf("highest price CPU: %5.2f\n", theList.stream().mapToDouble(CPU::getPrice).max().getAsDouble());
        System.out.printf("lowest price CPU: %5.2f\n", theList.stream().mapToDouble(CPU::getPrice).min().getAsDouble());
        System.out.println("Best Value CPU: " + Collections.max( theList, new CpuComparator() ).getCpuName());
    }


    /**
     *Create the report from the database table with the following format
     *
     * Intel Core i7-6700HQ @ 2.60GHz: $1,509.00
     * Intel Core i7-3770K @ 3.50GHz: $560.50
     * Intel Core i5-3570K @ 3.40GHz: $477.23
     * Intel Core i7-4700MQ @ 2.40GHz: $467.40
     */
    public void ShowReport()
    {
        System.out.print(this);
    }

    /**
     * Remove the CPU objects that do not have all of the required values parsed out of the line
     */
    public void CleanCpuList()
    {
        Iterator<CPU> it = theList.iterator();

        while( it.hasNext() )
        {
            CPU objCpu = it.next();

            if(objCpu.getValid() == false)
            {
                it.remove();
            }
        }
    }
}
