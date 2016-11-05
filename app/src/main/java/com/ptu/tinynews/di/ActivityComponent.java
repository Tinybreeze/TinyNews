package com.ptu.tinynews.di;

import android.app.Activity;

import com.ptu.tinynews.di.module.ActivityModule;
import com.ptu.tinynews.di.scope.ActivityScope;
import com.ptu.tinynews.module.main.activity.MainActivity;
import com.ptu.tinynews.module.yi.activity.NewsDetailActivity;
import com.ptu.tinynews.module.zhihu.activity.SectionActivity;
import com.ptu.tinynews.module.zhihu.activity.ZhihuDetailActivity;

import dagger.Component;

/**
 * Created by Administrator on 2016/10/16.
 */
@ActivityScope
@Component(dependencies = AppComponent.class,modules = ActivityModule.class)
public interface ActivityComponent
{
    Activity getActivity();

    void inject(MainActivity mainActivity);

    void inject(ZhihuDetailActivity zhihuDetailActivity);

    void inject(NewsDetailActivity newsDetailActivity);


    void inject(SectionActivity sectionActivity);
}
