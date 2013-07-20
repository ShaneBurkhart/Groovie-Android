package com.donkka.movie.app;

/**
 * Created by Shane on 6/9/13.
 */
public class FlixsterURL {
    public static final String API_KEY = "c2dhvn6k64escxu2s77bktkk";

    public static String getInTheatersURL(){
        return "http://api.rottentomatoes.com/api/public/v1.0/lists/movies/in_theaters.json?apikey=" + API_KEY;
    }

    public static String getUpcomingURL(){
        return "http://api.rottentomatoes.com/api/public/v1.0/lists/movies/upcoming.json?apikey=" + API_KEY;
    }

    public static String getSoonToDVDURL(){
        return "http://api.rottentomatoes.com/api/public/v1.0/lists/dvds/upcoming.json?apikey=" + API_KEY;
    }

    public static String getNewDVDUrl(){
        return "http://api.rottentomatoes.com/api/public/v1.0/lists/dvds/new_releases.json?apikey=" + API_KEY;
    }

    public static String getOnDVDUrl(){
        return "http://api.rottentomatoes.com/api/public/v1.0/lists/dvds/current_releases.json?apikey=" + API_KEY;
    }
}
