package com.donkka.movie.views;

import com.donkka.movie.models.MovieModel;

/**
 * Created by Shane on 6/11/13.
 */
public class SoonToDVDFragment extends TwoColumnMovieFragment {

    public SoonToDVDFragment(SlidingLayerDetails details){
        super(details);
    }

    @Override
    public MovieModel[] getMovies() {
        return MovieModel.getMoviesSoonToDVD();
    }

}
