package hess.fabian.filmverwaltung.mainActivity;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import hess.fabian.filmverwaltung.R;
import hess.fabian.filmverwaltung.mainActivity.movies.MoviesFragment;
import hess.fabian.filmverwaltung.mainActivity.series.SeriesFragment;

/**
 * Created by Fabian on 01.03.2018.
 */

public class MainPagerAdapter extends FragmentStatePagerAdapter {

    private Context context;

    public MainPagerAdapter(FragmentManager fm) {

        super(fm);
    }

    public void pagerAdapterInit(Context context) {
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MoviesFragment();
            case 1:
                return new SeriesFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return this.context.getString(R.string.movies_page_title);
            case 1:
                return this.context.getString(R.string.series_page_title);
            default:
                return null;
        }
    }
}
