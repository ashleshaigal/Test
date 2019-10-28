package com.example.watchmovie.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.watchmovie.Fragment_Home;
import com.example.watchmovie.Fragment_Movies;
import com.example.watchmovie.Fragment_Music;
import com.example.watchmovie.Fragment_originals;
import com.example.watchmovie.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3,R.string.tab_text_4};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).


        Fragment fragment = null;
        if (position == 0)
        {
            fragment = new Fragment_Home();

        }
        else if (position == 1)
        {
            fragment = new Fragment_Movies();
        }
        else if (position == 2)
        {
            fragment = new Fragment_Music();
        }
        else if (position == 3)
        {
            fragment = new Fragment_originals();
        }
        return fragment;


        //return PlaceholderFragment.newInstance(position + 1);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 4;
    }
}