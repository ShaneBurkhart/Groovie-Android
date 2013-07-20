package com.donkka.movie.views;

import com.donkka.movie.models.MovieModel;

/**
 * Created by Shane on 6/11/13.
 */
public class InTheatersFragment extends TwoColumnMovieFragment {

    public InTheatersFragment(SlidingLayerDetails details){
        super(details);
    }

    @Override
    public MovieModel[] getMovies() {
        return MovieModel.getMoviesInTheaters();
    }

}
