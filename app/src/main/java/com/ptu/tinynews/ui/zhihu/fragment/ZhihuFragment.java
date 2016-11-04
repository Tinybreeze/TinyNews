package com.ptu.tinynews.ui.zhihu.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.ptu.tinynews.R;
import com.ptu.tinynews.base.SimpleFragment;
import com.ptu.tinynews.ui.zhihu.adapter.ZhihuMainAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2016/10/19.
 */

public class ZhihuFragment extends SimpleFragment
{
    @BindView(R.id.tl_zhihu)
    TabLayout mTabLayout;
    @BindView(R.id.vp_zhihu)
    ViewPager mViewPager;

    List<Fragment> fragments = new ArrayList<>();

    String[] tabTitle = new String[]{"日报","专栏"};

    @Override
    protected int getLayoutId()
    {
        return R.layout.fragment_zhihu;
    }

    @Override
    protected void initEventAndData()
    {
        fragments.add(new DailyFragment());
        fragments.add(new SectionFragment());

        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mViewPager.setAdapter(new ZhihuMainAdapter(getChildFragmentManager(), fragments,tabTitle));
        mTabLayout.setupWithViewPager(mViewPager);

    }


}
