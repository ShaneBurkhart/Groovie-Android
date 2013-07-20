package com.donkka.movie.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.donkka.movie.R;
import com.donkka.movie.controllers.MainActivity;
import com.donkka.movie.lib.layer.SlidingLayer;

/**
 * Created by Shane on 6/9/13.
 */
public class SlidingLayerMenu extends SlidingLayer{

    private static final String[] MENU_ITEMS = {"Search Movies", "My Watchlist"};

    Context context;
    MainActivity a;
    ListView list;

    public SlidingLayerMenu(Context context){
        super(context);
        this.context = context;
        this.a = (MainActivity) context;
        config();
        addUI();
    }

    private void addUI(){
        LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = vi.inflate(R.layout.side_menu, null);
        list = (ListView) v.findViewById(R.id.side_menu_list);
        list.setAdapter(new ArrayAdapter<String>(context, R.layout.menu_item, R.id.side_menu_item, MENU_ITEMS));
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 0)
                    a.showMovieSearch();
                else
                    a.showMyWatchList();
                SlidingLayerMenu.this.closeLayer(true);
            }
        });
        this.addView(v);
    }

    private void config(){
        this.setShadowWidthRes(R.dimen.shadow_width);
        this.setShadowDrawable(R.drawable.sidebar_shadow);
        this.setStickTo(SlidingLayer.STICK_TO_RIGHT);
        this.setCloseOnTapEnabled(true);
    }
}
