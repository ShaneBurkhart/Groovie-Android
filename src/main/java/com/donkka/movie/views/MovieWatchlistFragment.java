package com.donkka.movie.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.donkka.movie.R;
import com.donkka.movie.app.MyPagerAdapter;
import com.donkka.movie.lib.tab.PagerSlidingTabStrip;

/**
 * Created by Shane on 6/9/13.
 */
public class MovieWatchlistFragment extends Fragment {

    public PagerSlidingTabStrip tabs;
    public ViewPager pager;
    public SlidingLayerDetails details;
    public MyPagerAdapter adapter;

    public MovieWatchlistFragment(SlidingLayerDetails details){
        this.details = details;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.movie_search, container, false);
        tabs = (PagerSlidingTabStrip) v.findViewById(R.id.tabs);
        pager = (ViewPager) v.findViewById(R.id.pager);
        pager.setOffscreenPageLimit(0);
        adapter = new MyPagerAdapter(getChildFragmentManager(), details);
        pager.setAdapter(adapter);
        tabs.setViewPager(pager);
        tabs.setIndicatorColorResource(R.color.secondary_color);
        tabs.setUnderlineColor(0x1AFFFFFF);
        tabs.setDividerColor(0x1AFFFFFF);
        return v;
    }

}
