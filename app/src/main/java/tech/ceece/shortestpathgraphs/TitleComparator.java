package tech.ceece.shortestpathgraphs;

import java.util.Comparator;

/**
 * This class compares the titles of two Movies.
 *
 * @author Yash Jain
 *         SBU ID: 109885836
 *         email: yash.jain@stonybrook.edu
 *         HW 7 CSE 214
 *         Section 10 Daniel Scanteianu
 *         Grading TA: Anand Aiyer
 */
public class TitleComparator implements Comparator {
    /**
     * Compares the two arguments based on the values of the titles
     * @param o1
     *      First Movie to be compared with
     * @param o2
     *       Second Movie being compared with
     * @return
     *      an int representing the difference in the movie titles.
     */
    @Override
    public int compare(Object o1, Object o2) {
        Movie movie1 = (Movie) o1;
        Movie movie2 = (Movie) o2;
        return (movie1.getTitle().compareTo(movie2.getTitle()));
    }
}
