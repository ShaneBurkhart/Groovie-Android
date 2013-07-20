package com.donkka.movie.app;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.donkka.movie.views.InTheatersFragment;
import com.donkka.movie.views.NewDVDFragment;
import com.donkka.movie.views.OnDVDFragment;
import com.donkka.movie.views.SlidingLayerDetails;
import com.donkka.movie.views.SoonToDVDFragment;
import com.donkka.movie.views.UpcomingMoviesFragment;

public class MyPagerAdapter extends FragmentPagerAdapter {

		private final String[] TITLES = { "Upcoming", "In Theaters", "Soon To DVD", "New DVD", "On DVD" };
        private SlidingLayerDetails details;

		public MyPagerAdapter(FragmentManager fm, SlidingLayerDetails details) {
			super(fm);
            this.details = details;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return TITLES[position];
		}

		@Override
		public int getCount() {
			return TITLES.length;
		}

		@Override
		public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new UpcomingMoviesFragment(details);
                case 1:
                    return new InTheatersFragment(details);
                case 2:
                    return new SoonToDVDFragment(details);
                case 3:
                    return new NewDVDFragment(details);
                case 4:
                    return new OnDVDFragment(details);
                default:
                    return new UpcomingMoviesFragment(details);
        }
		}

	}