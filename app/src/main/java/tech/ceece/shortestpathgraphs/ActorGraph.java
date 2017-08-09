package tech.ceece.shortestpathgraphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * This class will model the graph of Actors.
 *
 * @author Yash Jain
 *         SBU ID: 109885836
 *         email: yash.jain@stonybrook.edu
 *         HW 7 CSE 214
 *         Section 10 Daniel Scanteianu
 *         Grading TA: Anand Aiyer
 */
public class ActorGraph {
    //Data fields
    private static HashMap<String, Actor> actorsByName = new HashMap<>();
    private static HashMap<String, Movie> moviesByTitle = new HashMap<>();

    //Accessors

    /**
     * Returns the Hashmap for Actors
     * @return
     *      Hashmap<String, Actor>
     */
    public HashMap<String, Actor> getActorsByName(){
        return actorsByName;
    }

    /**
     * Returns the HashMap for movies
     * @return
     *      HashMap<String, Movie>
     */
    public HashMap<String, Movie> getMoviesByTitle(){
        return moviesByTitle;
    }

    //Methods

    /**
     * Returns breadth first traversal starting with the Actor with the passed in name.
     * @param name
     *       a String representating the name (key) of the Actor
     * @return
     *      a String representating the name (key) of the Actor
     */
    private static LinkedList<String> bfs(String name) throws ActorDoesNotExistException{
        if(actorsByName.get(name) == null)
            throw new ActorDoesNotExistException();

        LinkedList<String> linkedList = new LinkedList<>();
        LinkedList<Actor> queue = new LinkedList<>();
        linkedList.add(actorsByName.get(name).getName());
        actorsByName.get(name).setVisited(true);
        actorsByName.get(name).addPath(name);
        //System.out.println(name + "'s friends: ");
        //System.out.println("1. " + actorsByName.get(name).printPath());

        for(Actor friend : actorsByName.get(name).getFriends()){
            //System.out.println(friend.getName() + ", ");
            linkedList.add(friend.getName());
            friend.setVisited(true);
            friend.setPath(actorsByName.get(name).getPath());
            friend.addPath(friend.getName());
            //System.out.println("2. " + friend.printPath());
            queue.add(friend);
        }

        // System.out.println("\nQueue: ");

        while (!queue.isEmpty()){
            //System.out.println(queue.getFirst().getName() +"'s friends are: " );
            for(Actor actor : queue.getFirst().getFriends()){
                //System.out.print(actor.getName() + "(" + actor.isVisited() + ")" + ", ");
                if( !(actorsByName.get(actor.getName()).isVisited()) ) {
                    queue.add(actor);
                    actor.setPath(queue.getFirst().getPath());
                    actor.addPath(actor.getName());
                    //System.out.println("3. " + actor.printPath());
                    linkedList.add(actor.getName());
                    actorsByName.get(actor.getName()).setVisited(true);
                }
            }
            queue.removeFirst();
            System.out.println();
        }

//        System.out.println("\nResults:");
//
//        for(String key : actorsByName.keySet()){
//            System.out.println(actorsByName.get(key).getName() + "(" + actorsByName.get(key).isVisited() + ")");
//        }
        System.out.println();
        for(String key : actorsByName.keySet()){
            actorsByName.get(key).setVisited(false);
            //System.out.println(actorsByName.get(key).getName() + "(" + actorsByName.get(key).isVisited() + ")");
        }
        //System.out.println();

        return linkedList;
    }

    /**
     * Returns breadth first traversal starting with the Actor with the passed in name.
     * @param name
     *      a String representating the name (key) of the Actor
     * @return
     *      a String representating the name (key) of the Actor
     */
    public static LinkedList<String> getBfs(String name) throws ActorDoesNotExistException{
        return bfs(name);
    }

    /**
     * Adds an Actor in the Graph
     * @param name
     *      a String representing the name (key) of the Actor
     * @param actor
     *      an Actor object
     */
    public void addActor(String name, Actor actor) throws ActorAlreadyExistsException{
        if(actorsByName.get(name) != null)
            throw new ActorAlreadyExistsException();
        actorsByName.put(name, actor);
    }

    /**
     * Removes the Actor node from the Graph
     * @param key
     *      a String name representing the key for the Actor node
     */
    public void removeActor(String key){
        if(actorsByName.get(key) == null)
            throw new IllegalArgumentException();
        actorsByName.remove(key);
    }

    /**
     * Returns an actor by the given key passed
     * @param name
     *      a key representing the name of the actor
     * @return
     *      Actor
     * @throws ActorDoesNotExistException
     *      thrown when an Actor doesn't exist in the graph
     */
    public Actor getActor(String name) throws  ActorDoesNotExistException{
        if(actorsByName.get(name) == null)
            throw new ActorDoesNotExistException();
        return actorsByName.get(name);
    }

    /**
     * Adds a Movie in the Graph
     * @param title
     *      a String representing a title (key) of the Movie
     * @param movie
     *      a Movie objecte
     */
    public void addMovie(String title, Movie movie){
        if(moviesByTitle.get(title) != null)
            return;
        moviesByTitle.put(title, movie);
    }

    /**
     * Removes the Movie node from the Graph
     * @param key
     */
    public void removeMovie(String key){
        if(moviesByTitle.get(key) == null)
            throw new IllegalArgumentException();
        moviesByTitle.remove(key);
    }

    /**
     * Prints all the Actors in the graph.
     * @return
     *      String
     */
    public String printActors(){
        ArrayList<Actor> actors = new ArrayList<>();
        for(String key : actorsByName.keySet())
            actors.add(actorsByName.get(key));
        Collections.sort(actors, new NameComparator());

        String printActor = "";
        for(Actor actor : actors)
            printActor += actor.getName() + "\n";

        return printActor;
    }

    /**
     * Prints all the Movies in the graph.
     * @return
     *      String
     */
    public String printMovies(){
        ArrayList<Movie> movies = new ArrayList<>();
        for(String key : moviesByTitle.keySet())
            movies.add(moviesByTitle.get(key));
        Collections.sort(movies, new TitleComparator());

        String printMovies = "";
        for(Movie movie : movies)
            printMovies += movie.getTitle() + "\n";

        return printMovies;
    }




}
