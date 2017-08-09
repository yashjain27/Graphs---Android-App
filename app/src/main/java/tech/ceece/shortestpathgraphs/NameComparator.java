package tech.ceece.shortestpathgraphs;
import java.util.Comparator;

/**
 * This class compares the names of two Actors.
 *
 * @author Yash Jain
 *         SBU ID: 109885836
 *         email: yash.jain@stonybrook.edu
 *         HW 7 CSE 214
 *         Section 10 Daniel Scanteianu
 *         Grading TA: Anand Aiyer
 */
public class NameComparator implements Comparator {
    /**
     * Compared the two Actor objects based on the values of the names.
     * @param o1
     *       First Actor to be compared with
     * @param o2
     *       Second Actor to be compared to
     * @return
     *      an int representing the difference in the name
     */
    @Override
    public int compare(Object o1, Object o2) {
        Actor actor1 = (Actor) o1;
        Actor actor2 = (Actor) o2;
        return (actor1.getName().compareTo(actor2.getName()));
    }
}
