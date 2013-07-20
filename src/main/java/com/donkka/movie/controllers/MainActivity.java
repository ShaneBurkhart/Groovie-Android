package com.donkka.movie.controllers;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;

import com.donkka.movie.R;
import com.donkka.movie.app.data.FileCache;
import com.donkka.movie.lib.layer.SlidingLayer;
import com.donkka.movie.views.MovieTabFragment;
import com.donkka.movie.views.MovieWatchlistFragment;
import com.donkka.movie.views.SlidingLayerDetails;
import com.donkka.movie.views.SlidingLayerMenu;

public class MainActivity extends FragmentActivity {

    RelativeLayout container, fragmentContainer;
    public SlidingLayer menuLayer, detailLayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Initialize FileCache. It needs actvity for files
        FileCache.init(this);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        //Master Container
        container = (RelativeLayout) findViewById(R.id.parent_container);
        fragmentContainer = (RelativeLayout) findViewById(R.id.fragment_container);

        //Init UI
        initUI();
        showMovieSearch();

        //Add Menu Listener
        addMenuListener();
    }

    private void initUI(){
        //Adding Sliding Layers
        menuLayer = new SlidingLayerMenu(this);
        container.addView(menuLayer);
        detailLayer = new SlidingLayerDetails(this);
        container.addView(detailLayer);
    }

    public void showMovieSearch(){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, new MovieTabFragment((SlidingLayerDetails) this.detailLayer)).commit();
    }

    public void showMyWatchList(){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, new MovieWatchlistFragment((SlidingLayerDetails) this.detailLayer)).commit();
    }

    private void addMenuListener(){
        System.out.println("Test");
        findViewById(R.id.menu_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!menuLayer.isOpened())
                    menuLayer.openLayer(true);
            }
        });
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_MENU){
            //Open menu slider
            if(!menuLayer.isOpened())
                menuLayer.openLayer(true);
            else
                menuLayer.closeLayer(true);
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        if(detailLayer.isOpened() || menuLayer.isOpened()){
            detailLayer.closeLayer(true);
            menuLayer.closeLayer(true);
            return;
        }
        super.onBackPressed();
    }
}
