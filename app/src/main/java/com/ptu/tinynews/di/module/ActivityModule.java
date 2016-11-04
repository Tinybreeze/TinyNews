package com.ptu.tinynews.di.module;


import android.app.Activity;

import com.ptu.tinynews.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;


/**
 * Created by Administrator on 2016/10/18.
 */
@Module
public class ActivityModule
{
    private Activity mActivity;

    public ActivityModule(Activity activity)
    {
        this.mActivity = activity;
    }

    @Provides
    @ActivityScope
    public Activity provideActivity()
    {
        return mActivity;
    }
}
