package tech.ceece.shortestpathgraphs;

import java.util.ArrayList;

/**
 * This class will represent a Movie that an Actor could be in.
 *
 * @author Yash Jain
 *         SBU ID: 109885836
 *         email: yash.jain@stonybrook.edu
 *         HW 7 CSE 214
 *         Section 10 Daniel Scanteianu
 *         Grading TA: Anand Aiyer
 */
public class Movie {
    //Data fields
    private String title;
    private ArrayList<Actor> actors = new ArrayList<>();
    private int year;

    //Constructor

    /**
     * Constructor for the Movie class
     * @param title
     *      a String representing the title of this Movie object
     */
    public Movie(String title){
        this.title = title;
    }

    //Accessors

    /**
     * Returns the title of the Movie.
     * @return
     *      String
     */
    public String getTitle(){
        return  title;
    }

    /**
     * Returns an ArrayList of actors in the given movie
     * @return
     *      ArrayList<Actor>
     */
    public ArrayList<Actor> getActors(){
        return actors;
    }

    /**
     * Returns the year of the Movie's
     * @return
     *      int
     */
    public int getYear(){
        return year;
    }

    //Mutators

    /**
     * Sets the the title of the movie
     * @param title
     *      a String representing the new title of the movie.
     */
    public void setTitle(String title){
        this.title = title;
    }

    /**
     * Adds an actor to the Actor list
     * @param actor
     *      Actor object
     */
    public void setActors(Actor actor){
        actors.add(actor);
    }

    /**
     * Sets the year of the Movie's premiere.
     * @param year
     *      an int value representing the year the movie was made.
     */
    public void setYear(int year){
        this.year = year;
    }
}
