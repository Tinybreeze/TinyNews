package com.ptu.tinynews.di;

import android.app.Activity;

import com.ptu.tinynews.di.module.FragmentModule;
import com.ptu.tinynews.di.scope.FragmentScope;
import com.ptu.tinynews.ui.yi.fragment.NewsListFragment;
import com.ptu.tinynews.ui.zhihu.fragment.DailyFragment;
import com.ptu.tinynews.ui.zhihu.fragment.SectionFragment;

import dagger.Component;

/**
 * Created by Administrator on 2016/10/18.
 */
@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent
{
    Activity getActivity();

    void inject(DailyFragment dailyFragment);

    void inject(NewsListFragment newsListFragment);

    void inject(SectionFragment sectionFragment);
}
