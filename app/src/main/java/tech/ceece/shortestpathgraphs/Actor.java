package tech.ceece.shortestpathgraphs;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * This class will represent an Actor in the graph
 *
 * @author Yash Jain
 *         SBU ID: 109885836
 *         email: yash.jain@stonybrook.edu
 *         HW 7 CSE 214
 *         Section 10 Daniel Scanteianu
 *         Grading TA: Anand Aiyer
 */
public class Actor {
    //Data fields
    private String name;
    private ArrayList<Movie> movies = new ArrayList<>();
    private ArrayList<Actor> friends = new ArrayList<>();
    private boolean visited;
    private LinkedList<String> path = new LinkedList<>();

    //Constructor

    /**
     * Constructor for the Actor class
     */
    public Actor(){
        visited = false;
    }

    //Getters and Setters

    /**
     * Returns the name of the actor
     * @return
     *      String
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the Actor
     * @param name
     *      a String representing the name of the Actor
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the movies this Actor is in
     * @return
     *      ArrayList<Movie>
     */
    public ArrayList<Movie> getMovies() {
        return movies;
    }

    /**
     * Sets the Movie in the ArrayList of Movies
     * @param movie
     *      a Movie object passed
     */
    public void setMovies(Movie movie) {
        movies.add(movie);
    }

    /**
     * Returns the list of of friends this Actor is friends with
     * @return
     *      ArrayList<Actor>
     */
    public ArrayList<Actor> getFriends() {
        return friends;
    }

    /**
     * Sets the Actor object passed to the current Actor's friends list
     * @param friend
     *      an Actor object
     */
    public void setFriends(Actor friend) {
        for(Actor actor : friends){
            if(actor.getName().equals(friend.getName()))
                return;
        }
        friends.add(friend);
    }

    /**
     * Returns the value of whether this node has been marked or not
     * @return
     *      boolean
     */
    public boolean isVisited() {
        return visited;
    }

    /**
     * Sets the value of the isVisited node
     * @param visited
     *      boolean value representing the node's mark
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    /**
     * Returns the path of the Actor
     * @return
     *     LinkedList<String>
     */
    public LinkedList<String> getPath() {
        return path;
    }

    /**
     * Sets the new path of the Actor from the given parameter
     * @param setPath
     *      a LinkedList<String> representation of a given path
     */
    public void setPath(LinkedList<String> setPath){
        path = new LinkedList<>();
        path.addAll(setPath);
    }

    /**
     * Appends a name to the end of the path
     * @param setName
     *      a String name to be added to the path
     */
    public void addPath(String setName){
        for(String s : path)
            if(s.equals(setName))
                return;
        path.add(setName);
    }

    /**
     * Returns a string representation of the Actor which includes his names and movies
     * @return
     *      String
     */
    public String toString(){
        String actorInfo  = "Actor: " + name + " Friends: ";

        //Friend names
        for(Actor actor : friends){
            actorInfo += actor.getName() + ", ";
        }

        //Movies
        actorInfo += "\n" + name + " Movies: ";

        for (Movie movie : movies){
            actorInfo += movie.getTitle() + "(" + movie.getYear() + "), ";
        }

        return actorInfo;
    }

    /**
     * The current path up to the current Actor in the given traversal.
     * @return
     *      a String representation of the path
     */
    public String printPath(){
        String printable = "";
        for(String s : path)
            printable += s + ", ";
        return printable;
    }
}
