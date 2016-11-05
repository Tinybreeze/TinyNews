package com.ptu.tinynews.module.yi.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ptu.tinynews.module.yi.fragment.NewsListFragment;

import java.util.List;

/**
 * Created by Administrator on 2016/10/25.
 */

public class WangyiMainAdapter extends FragmentPagerAdapter
{
    private List<NewsListFragment> fragments;
    private List<String> tabTitle;
    public WangyiMainAdapter(FragmentManager fm, List<NewsListFragment> fragments, List<String> tabTitle)
    {
        super(fm);
        this.fragments = fragments;
        this.tabTitle=tabTitle;
    }
    @Override
    public Fragment getItem(int position)
    {
        return fragments.get(position);
    }

    @Override
    public int getCount()
    {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return tabTitle.get(position);
    }
}
