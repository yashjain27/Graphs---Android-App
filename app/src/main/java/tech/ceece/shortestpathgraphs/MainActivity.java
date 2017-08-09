package tech.ceece.shortestpathgraphs;

import big.data.*;
import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.ActivityCompat;
import java.util.LinkedList;

public class MainActivity extends Activity {
    ActorGraph graph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        graph = new ActorGraph();
//        ActivityCompat.requestPermissions(this,
//                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
//                123);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
    }

    public void onSearch(View v){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.pritnable);
        dialog.show();

        //generate the web-address for the movie
        String prefix= "http://www.omdbapi.com/?t=";
        EditText editText = (EditText) findViewById(R.id.editText);
        String title = editText.getText().toString();
        String postfix="&y=&plot=short&r=xml";
        if(title.length()>0){
            DataSource ds = DataSource.connectXML(prefix+title.replace(' ','+')+postfix);
            ds.load();
            try {
                TextView textView = new TextView(this);
                String something = ds.fetchString("movie/title") + "(" +
                        ds.fetchInt("movie/year") + ") Starring: "
                        + ds.fetchString("movie/actors") + "\n";
                textView.setText(something);
                String[] actors = ds.fetchString("movie/actors").split(", ");

                ViewGroup viewGroup = (ViewGroup) dialog.findViewById(R.id.printable);
                viewGroup.addView(textView );

                //Create Movie object
                Movie movie = new Movie(ds.fetchString("movie/title"));
                movie.setYear(ds.fetchInt("movie/year"));

                for(int i = 0; i < actors.length; i++){
                    Actor actor = new Actor();
                    actor.setName(actors[i]);
                    actor.setMovies(movie);
                    movie.setActors(actor);
                    try {
                        graph.addActor(actors[i], actor);
                    } catch (ActorAlreadyExistsException e) {
                        graph.getActor(actors[i]).setMovies(movie);
                    }
                }
                //Set up each actor's friends in the current movie
                for(Actor a : movie.getActors())
                    for(Actor b : movie.getActors())
                        if(!a.equals(b))
                            graph.getActor(a.getName()).setFriends(graph.getActor(b.getName()));

                //Set up the friends of actors that already existed and didn't get modified
                for(String compareMovie : graph.getMoviesByTitle().keySet())
                    for(Actor actor1 : graph.getMoviesByTitle().get(compareMovie).getActors())
                        for(Actor a : movie.getActors())
                            if(actor1.getName().equals(a.getName()))
                                for(Actor actor : movie.getActors())
                                    if(!actor1.getName().equals(actor.getName()))
                                        actor1.setFriends(actor);

                graph.addMovie(ds.fetchString("movie/title"), movie);
            } catch (Exception e) {
                Toast.makeText(this, "Movie not found!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void onPrintActors(View v){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.pritnable);
        dialog.show();
        TextView textView = new TextView(this);
        textView.setText(graph.printActors());
        ViewGroup viewGroup = (ViewGroup) dialog.findViewById(R.id.printable);
        viewGroup.addView(textView );
    }

    public void onPrintMovies(View v){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.pritnable);
        dialog.show();
        TextView textView = new TextView(this);
        textView.setText(graph.printMovies());
        ViewGroup viewGroup = (ViewGroup) dialog.findViewById(R.id.printable);
        viewGroup.addView(textView );
    }

    public void onFind(View v){
        Dialog d = new Dialog(this);
        d.setContentView(R.layout.pritnable);
        d.show();
        EditText editText = (EditText) findViewById(R.id.editText2);
        EditText editText1 = (EditText) findViewById(R.id.editText3);
        for(String act : graph.getActorsByName().keySet()){
            graph.getActorsByName().get(act).getPath().clear();
        }
        try {
            ActorGraph.getBfs(editText.getText().toString());
            TextView textView = new TextView(this);
            textView.setText(graph.getActor(editText1.getText().toString()).printPath());
            ViewGroup viewGroup = (ViewGroup) d.findViewById(R.id.printable);
            viewGroup.addView(textView );
        } catch (ActorDoesNotExistException e) {
            Toast.makeText(this, "Actor doesn't exist!", Toast.LENGTH_SHORT).show();
        }
    }

    public void onBfs(View v){
        Dialog d = new Dialog(this);
        d.setContentView(R.layout.pritnable);
        d.show();
        LinkedList<String> actorBfs;
        EditText editText = (EditText) findViewById(R.id.editText4);

        try {
            actorBfs = ActorGraph.getBfs(editText.getText().toString());
            String bfs = "";
            for(String s : actorBfs)
               bfs += (s + ", ");
            TextView textView = new TextView(this);
            textView.setText(bfs);
            ViewGroup viewGroup = (ViewGroup) d.findViewById(R.id.printable);
            viewGroup.addView(textView);
        } catch (ActorDoesNotExistException e) {
            Toast.makeText(this, "Actor doesn't exist!", Toast.LENGTH_SHORT).show();
        }
    }

    public void onLookup(View v){
        Dialog d = new Dialog(this);
        d.setContentView(R.layout.pritnable);
        d.show();
        EditText editText1 = (EditText) findViewById(R.id.editText5);
        try {
            TextView textView = new TextView(this);
            textView.setText(graph.getActor(editText1.getText().toString()).toString());
            ViewGroup viewGroup = (ViewGroup) d.findViewById(R.id.printable);
            viewGroup.addView(textView);
        } catch (ActorDoesNotExistException e) {
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        }
    }

    public void onExit(View v){
        Toast.makeText(this, "Thanks for playing!", Toast.LENGTH_SHORT).show();
        finish();
    }

}
