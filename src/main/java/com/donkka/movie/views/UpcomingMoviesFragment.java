package com.donkka.movie.views;

import com.donkka.movie.models.MovieModel;

/**
 * Created by Shane on 6/11/13.
 */
public class UpcomingMoviesFragment extends TwoColumnMovieFragment {

    public UpcomingMoviesFragment(SlidingLayerDetails details){
        super(details);
    }

    @Override
    public MovieModel[] getMovies() {
        return MovieModel.getMoviesUpcoming();
    }

}
