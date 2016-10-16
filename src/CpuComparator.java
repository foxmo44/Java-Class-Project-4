import java.util.Comparator;
/**
 * <h1>Advanced Java - Project 4</h1>
 * <h1>CpuComparator Class</h1>
 * This class provide a function used to compare the CPU objects
 * <p>
 *
 * @author   Michael Fox
 * @version  Project 04
 * @since   2016.10.16
 */
public class CpuComparator implements Comparator< CPU >
{
    /**
     * Compare function to return which cpu has better performance
     * @param c1 - first cpu object
     * @param c2 - second cpu object
     * @return indicate the value that tells the result of the compare
     */
    public int compare(CPU c1, CPU c2 )
    {
        double c1Value =  c1.getValue();
        double c2Value = c2.getValue();

        return (int)(c1Value - c2Value);
    }
}
