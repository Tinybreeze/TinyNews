package com.ptu.tinynews.util;

import android.content.Context;
import android.net.ConnectivityManager;

import com.ptu.tinynews.app.App;

/**
 * Created by Administrator on 2016/10/18.
 */

public class SystemUtil
{
    /**
     * 检查是否有可用网络
     * @return
     */
    public static boolean isNetworkConnected()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager)App.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null;
    }

    //    public static void copyToClipBoard(Context context ,String text)
//    {
//        ClipData clipData = ClipData.newPlainText(text);
//        ClipboardManager manager = (ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
//        manager.setPrimaryClip(clipData);
//
//    }
    public static int dpTopx(Context context, float dp)
    {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public static int dxTodp(Context context, float dx)
    {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dx / scale + 0.5f);
    }
}
