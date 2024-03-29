package com.wenchao.supersplash;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class ParallaxPagerAdapter extends FragmentPagerAdapter {

    private List<ParallaxFragment> fragmentList;

    public ParallaxPagerAdapter(@NonNull FragmentManager fm, List<ParallaxFragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
