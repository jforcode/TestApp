package com.example.jeevan.testapp.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.example.jeevan.testapp.fragments.Test2ContentFragment;
import com.example.jeevan.testapp.fragments.Test2FavoritesFragment;

import java.util.HashMap;

/**
 * Created by jeevan on 5/21/17.
 */

public class Test2ViewPagerAdapter extends FragmentPagerAdapter {
    public static final int IND_CONTENT = 0;
    public static final int IND_FAVORITES = 1;

    HashMap<Integer, Fragment> fragmentMap;

    public Test2ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        fragmentMap = new HashMap<>();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        fragmentMap.put(position, fragment);
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        fragmentMap.remove(position);
        super.destroyItem(container, position, object);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return new Test2ContentFragment();
            case 1: return new Test2FavoritesFragment();
        }
        return null;
    }

    public Fragment getItemAt(int position) {
        return fragmentMap.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0: return "Main";
            case 1: return "Favorites";
        }
        return "";
    }

    @Override
    public int getCount() {
        return 2;
    }
};
