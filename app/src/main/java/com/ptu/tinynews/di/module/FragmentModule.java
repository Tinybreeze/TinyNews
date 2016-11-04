package com.ptu.tinynews.di.module;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.ptu.tinynews.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2016/10/18.
 */
@Module
public class FragmentModule
{
    private Fragment mfragment;

    public FragmentModule(Fragment fragment)
    {
        this.mfragment = fragment;
    }

    @Provides
    @FragmentScope
    public Activity provideActivity()
    {
        return mfragment.getActivity();
    }

}
