package com.ptu.tinynews.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.ptu.tinynews.di.AppComponent;
import com.ptu.tinynews.di.DaggerAppComponent;
import com.ptu.tinynews.di.module.AppModule;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2016/10/16.
 */

public class App extends Application
{
    private static App instance;
    private Set<Activity> allActivitys;
    public static int SCREEN_WIDTH = -1;
    public static int SCREEN_HEIGHT = -1;
    public static float DIMEN_RATE = -1.0F;
    public static int DIMEN_DPI = -1;
    public static synchronized App getInstance()
    {
        return instance;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        instance=this;
        //初始化屏幕宽高
        getScreenSize();
    }

    public void addActivity(Activity activity)
    {
        if (allActivitys == null)
        {
            allActivitys = new HashSet<Activity>()
            {
            };
        }
        allActivitys.add(activity);
    }

    public void removeActivity(Activity activity)
    {
        if (allActivitys != null)
        {
            allActivitys.remove(activity);
        }
    }

    public void exitApp()
    {
        if (allActivitys != null)
        {
            synchronized (allActivitys)
            {
                for (Activity activity : allActivitys)
                {
                    activity.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
    public  static AppComponent getAppComponent()
    {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(instance))
                .build();
    }
    public void getScreenSize() {
        WindowManager windowManager = (WindowManager)this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        Display display = windowManager.getDefaultDisplay();
        display.getMetrics(dm);
        DIMEN_RATE = dm.density / 1.0F;
        DIMEN_DPI = dm.densityDpi;
        SCREEN_WIDTH = dm.widthPixels;
        SCREEN_HEIGHT = dm.heightPixels;
        if(SCREEN_WIDTH > SCREEN_HEIGHT) {
            int t = SCREEN_HEIGHT;
            SCREEN_HEIGHT = SCREEN_WIDTH;
            SCREEN_WIDTH = t;
        }
    }
}
