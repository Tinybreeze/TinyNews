package com.ptu.tinynews.di;

import com.ptu.tinynews.app.App;
import com.ptu.tinynews.di.scope.ContextLife;
import com.ptu.tinynews.di.module.AppModule;
import com.ptu.tinynews.model.http.RetrofitHelper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Administrator on 2016/10/18.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent
{
    //提供application的Context
    @ContextLife("Application")
    App getContext();

    //提供http的帮助类
    RetrofitHelper retrofitHelper();
}
