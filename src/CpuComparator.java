// Author: Michael Fox
// Session: Advanced Java
// Project: Project 4
// Current Date: 10/5/2016
// DateDue: 2016.10.12
import java.util.Comparator;

public class CpuComparator implements Comparator< CPU >
{
    /**
     * Compare function to return which cpu has better performance
     * @param c1 - first cpu object
     * @param c2 - second cpu object
     * @return
     */
    public int compare(CPU c1, CPU c2 )
    {
        double c1Value =  c1.getValue();
        double c2Value = c2.getValue();

        return (int)(c1Value - c2Value);
    }
}
