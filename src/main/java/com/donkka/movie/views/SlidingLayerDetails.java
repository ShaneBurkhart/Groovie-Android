package com.donkka.movie.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.donkka.movie.R;
import com.donkka.movie.app.data.MemoryCache;
import com.donkka.movie.lib.layer.SlidingLayer;
import com.donkka.movie.models.MovieModel;

/**
 * Created by Shane on 6/9/13.
 */
public class SlidingLayerDetails extends SlidingLayer{

    Context context;
    TextView title, synopsis;
    ImageView image;
    RelativeLayout container;

    public SlidingLayerDetails(Context context){
        super(context);
        this.context = context;
        config();
        addUI();
    }

    private void addUI(){
        LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = vi.inflate(R.layout.movie_detail_layout, null);
        this.title = (TextView) v.findViewById(R.id.movie_title);
        this.synopsis = (TextView) v.findViewById(R.id.detail_synopsis);
        this.image = (ImageView) v.findViewById(R.id.detail_movie_image);
        this.addView(v);
    }

    private void config(){
        this.setShadowWidthRes(R.dimen.shadow_width);
        this.setShadowDrawable(R.drawable.sidebar_shadow);
        this.setStickTo(SlidingLayer.STICK_TO_LEFT);
        this.setCloseOnTapEnabled(true);
    }

    public void show(MovieModel movie){
        if(movie != null && !this.isOpened()){
            title.setText(movie.title);
            synopsis.setText(movie.synopsis);
            image.setImageBitmap(MemoryCache.get(movie.imageUrl));
            this.openLayer(true);
        }
    }
}
