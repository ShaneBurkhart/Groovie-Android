package com.donkka.movie.models;

import com.donkka.movie.app.FlixsterURL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by Shane on 6/9/13.
 */
public class MovieModel {
    private static final int NUM_API_TRIES = 3;

    public String title, imageUrl, synopsis, releaseDate, releaseType, rating;
    private String data, releases;
    private static int i = 0;

    public MovieModel(String title){
        this.title = title;
    }

    public MovieModel(JSONObject o){
        try{
            this.data = o.toString();
            if(o.has("title"))
                this.title = o.getString("title");
            if(o.has("posters")){
                JSONObject i = (JSONObject) o.get("posters");
                if(i.has("profile")){
                    imageUrl = i.getString("detailed");
                }
            }
            if(o.has("synopsis"))
                this.synopsis = o.getString("synopsis");
            if(o.has("release-dates")){
                this.releases = o.getString("release-dates");
                JSONObject t = new JSONObject(this.releases);
                if(t.has("theaters"))
                    this.releaseDate = t.getString("theater");
                else
                    this.releaseDate = "Already Out";
            }
            if(o.has("mpaa_rating"))
                this.rating = o.getString("mpaa_rating");
        } catch (JSONException e){
            System.out.println(e.getMessage());
        }
    }

    public static MovieModel[] getMoviesInTheaters(){
        i = 0;
        while(i++ < NUM_API_TRIES){
            try{
                URL url = new URL(FlixsterURL.getInTheatersURL());
                return getMoviesFromResponse(url.openConnection().getInputStream());
            }catch (IOException e){
                System.out.println("IOError " + e.getMessage());
                System.out.println("MovieModel: Retrying....");
                continue;
            }
        }
        return null;
    }

    public static MovieModel[] getMoviesUpcoming(){
        i = 0;
        while(i++ < NUM_API_TRIES){
            try{
                URL url = new URL(FlixsterURL.getUpcomingURL());
                return getMoviesFromResponse(url.openConnection().getInputStream());
            }catch (IOException e){
                System.out.println("IOError " + e.getMessage());
                System.out.println("MovieModel: Retrying....");
                continue;
            }
        }
        return null;
    }

    public static MovieModel[] getMoviesSoonToDVD(){
        i = 0;
        while(i++ < NUM_API_TRIES){
            try{
                URL url = new URL(FlixsterURL.getSoonToDVDURL());
                return getMoviesFromResponse(url.openConnection().getInputStream());
            }catch (IOException e){
                System.out.println("IOError " + e.getMessage());
                System.out.println("MovieModel: Retrying....");
                continue;
            }
        }
        return null;
    }

    public static MovieModel[] getMoviesOnDVD(){
        i = 0;
        while(i++ < NUM_API_TRIES){
            try{
                URL url = new URL(FlixsterURL.getOnDVDUrl());
                return getMoviesFromResponse(url.openConnection().getInputStream());
            }catch (IOException e){
                System.out.println("IOError " + e.getMessage());
                System.out.println("MovieModel: Retrying....");
                continue;
            }
        }
        return null;
    }

    public static MovieModel[] getMoviesNewDVD(){
        i = 0;
        while(i++ < NUM_API_TRIES){
            try{
                URL url = new URL(FlixsterURL.getNewDVDUrl());
                return getMoviesFromResponse(url.openConnection().getInputStream());
            }catch (IOException e){
                System.out.println("IOError " + e.getMessage());
                System.out.println("MovieModel: Retrying....");
                continue;
            }
        }
        return null;
    }

    private static MovieModel[] getMoviesFromResponse(InputStream is){
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuffer b = new StringBuffer();
            String s;
            while((s = reader.readLine())!= null)
                b.append(s);
            JSONObject o = new JSONObject(b.toString().trim());
            reader.close();
            JSONArray m = (JSONArray) o.get("movies");
            MovieModel[] movies = new MovieModel[m.length()];
            for(int i = 0 ; i < m.length() ; i ++)
                movies[i] = new MovieModel((JSONObject) m.get(i));
            return movies;
        }catch (IOException e){
            System.out.println("IOError " + e.getMessage());
            return null;
        } catch (JSONException e){
            System.out.println("JSONError " + e.getMessage());
            return null;
        }
    }
}
