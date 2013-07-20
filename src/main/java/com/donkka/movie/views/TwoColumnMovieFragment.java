package com.donkka.movie.views;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.donkka.movie.R;
import com.donkka.movie.app.data.Executor;
import com.donkka.movie.app.data.ImageLoader;
import com.donkka.movie.lib.scaleimage.ScaleImageView;
import com.donkka.movie.models.MovieModel;

/**
 * Created by Shane on 6/9/13.
 */
public abstract class TwoColumnMovieFragment extends Fragment{

    private MovieModel[] movies;
    private SlidingLayerDetails details;
    private LinearLayout left, right;
    private Handler handler = new Handler();

    public TwoColumnMovieFragment(SlidingLayerDetails details){
        this.details= details;
    }

    public abstract MovieModel[] getMovies();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.loading_layout, container, false);
        Executor.run(new MovieLoaderRunnable());
        return (RelativeLayout) v;
    }

    public void onGridItemClick(int position) {
        Activity activity = getActivity();

        if (activity != null) {
            MovieModel movie = movies[position];
            details.show(movie);
        }
    }

    private class MovieClickListener implements View.OnClickListener {

        int pos = 0;

        public MovieClickListener(int pos){
            this.pos = pos;
        }

        @Override
        public void onClick(android.view.View view) {
            onGridItemClick(pos);
        }
    }

    private class MovieLoaderRunnable implements Runnable{

        @Override
        public void run() {
            if((TwoColumnMovieFragment.this.movies = getMovies()) != null)
                this.initUI(TwoColumnMovieFragment.this.movies);
            else
                TwoColumnMovieFragment.this.handler.post(new ShowErrorToFragmentRunner());
        }

        private void initUI(MovieModel[] movies){
            Activity activity = getActivity();
            LayoutInflater inflater = activity.getLayoutInflater();
            if (activity != null && inflater != null && movies != null) {
                View v = inflater.inflate(R.layout.two_column_movie_list,  null);
                left = (LinearLayout) v.findViewById(R.id.two_column_left);
                right = (LinearLayout) v.findViewById(R.id.two_column_right);
                int i = 0;
                int j = 0;
                for(MovieModel m : movies){
                    View iv = inflater.inflate(R.layout.two_column_movie_item, null);
                    ScaleImageView image = (ScaleImageView) iv.findViewById(R.id.two_column_movie_image);
                    image.setImageResource(android.R.drawable.alert_dark_frame);
                    Executor.run(new ImageLoader(m.imageUrl, image, handler));
                    image.setOnClickListener(new MovieClickListener(j));
                    if(i == 0)
                        left.addView(iv);
                    else
                        right.addView(iv);
                    if(i == 0)
                        i++;
                    else
                        i = 0;
                    j++;
                }
                handler.post(new AddViewToFragmentRunner(v));
            }
        }
    }

    private class ShowErrorToFragmentRunner implements Runnable{
        @Override
        public void run() {
            ViewGroup layout = (ViewGroup) TwoColumnMovieFragment.this.getView();
            if(layout != null){
                TextView tv = new TextView(TwoColumnMovieFragment.this.getActivity());
                tv.setText("There is a problem.  Sorry please try again later.");
                layout.removeAllViews();
                layout.addView(tv);
            }
        }
    }

    private class AddViewToFragmentRunner implements Runnable{
        private View v;
        private AddViewToFragmentRunner(View v){
            this.v = v;
        }

        @Override
        public void run() {
            ViewGroup layout = (ViewGroup) TwoColumnMovieFragment.this.getView();
            if(layout != null){
                layout.removeAllViews();
                layout.addView(v);
            }
        }
    }
}