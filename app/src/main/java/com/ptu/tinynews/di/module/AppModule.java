package com.ptu.tinynews.di.module;

import com.ptu.tinynews.app.App;
import com.ptu.tinynews.di.scope.ContextLife;
import com.ptu.tinynews.model.http.RetrofitHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2016/10/16.
 */
@Module
public class AppModule
{
    private final App application;

    public AppModule(App application)
    {
        this.application = application;
    }

    @ContextLife("Application")
    @Singleton
    @Provides
    App provideApplicationContext()
    {
        return application;
    }

    @Singleton
    @Provides
    RetrofitHelper provideRetrofitHelper()
    {
        return new RetrofitHelper();
    }
}
