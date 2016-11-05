package com.ptu.tinynews.module.zhihu.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/10/19.
 */

public class ZhihuMainAdapter extends FragmentPagerAdapter
{
    private List<Fragment> fragments;
    private String[] tabTitle;
    public ZhihuMainAdapter(FragmentManager fm, List<Fragment> fragments,String[] tabTitle)
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
        return tabTitle[position];
    }
}
