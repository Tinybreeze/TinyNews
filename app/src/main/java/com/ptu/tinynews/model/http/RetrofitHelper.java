package com.ptu.tinynews.model.http;

import com.ptu.tinynews.BuildConfig;
import com.ptu.tinynews.app.Constant;
import com.ptu.tinynews.model.bean.CommentBean;
import com.ptu.tinynews.model.bean.DailyBeforeListBean;
import com.ptu.tinynews.model.bean.DailyListBean;
import com.ptu.tinynews.model.bean.DetailExtraBean;
import com.ptu.tinynews.model.bean.HotListBean;
import com.ptu.tinynews.model.bean.NewsDetail;
import com.ptu.tinynews.model.bean.NewsSummary;
import com.ptu.tinynews.model.bean.SectionChildListBean;
import com.ptu.tinynews.model.bean.SectionListBean;
import com.ptu.tinynews.model.bean.ThemeChildListBean;
import com.ptu.tinynews.model.bean.ThemeListBean;
import com.ptu.tinynews.model.bean.WelcomeBean;
import com.ptu.tinynews.model.bean.ZhihuDetailBean;
import com.ptu.tinynews.util.SystemUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by Administrator on 2016/10/18.
 */

public class RetrofitHelper
{

    /**
     * 设缓存有效期为两天
     */
    private static final long CACHE_STALE_SEC = 60 * 60 * 24 * 2;

    /**
     * 查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
     * max-stale 指示客户机可以接收超出超时期间的响应消息。如果指定max-stale消息的值，那么客户机可接收超出超时期指定值之内的响应消息。
     */
    private static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;

    /**
     * 查询网络的Cache-Control设置，头部Cache-Control设为max-age=0
     * (假如请求了服务器并在a时刻返回响应结果，则在max-age规定的秒数内，浏览器将不会发送对应的请求到服务器，数据由缓存直接返回)时则不会使用缓存而请求服务器
     */
    private static final String CACHE_CONTROL_AGE = "max-age=0";
    private static OkHttpClient okHttpClient = null;
    private static ZhihuApis zhihuApiService = null;
    private static WangyiApis wangyiApiService = null;

    public RetrofitHelper()
    {
        init();
    }

    public static ZhihuApis getZhihuApiService()
    {
        Retrofit zhihuRetrofit = new Retrofit.Builder()
                .baseUrl(ZhihuApis.HOST)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return zhihuRetrofit.create(ZhihuApis.class);
    }

    public static WangyiApis getWangyiApiServiece()
    {
        Retrofit wangyiRetrofit = new Retrofit.Builder()
                .baseUrl(WangyiApis.NETEAST_HOST)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return wangyiRetrofit.create(WangyiApis.class);
    }

    private void init()
    {
        initOKHttp();
        zhihuApiService = getZhihuApiService();
        wangyiApiService = getWangyiApiServiece();
    }

    private void initOKHttp()
    {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //debug状态下LOG日志输出设置
        if (BuildConfig.DEBUG)
        {
            // https://drakeet.me/retrofit-2-0-okhttp-3-0-config
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            builder.addInterceptor(loggingInterceptor);
        }
        // http://www.jianshu.com/p/93153b34310e
        File cacheFile = new File(Constant.PATH_CACHE);
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);

        Interceptor cacheInterceptor = new Interceptor()
        {
            @Override
            public Response intercept(Chain chain) throws IOException
            {
                Request request = chain.request()
                        .newBuilder()
                        .header("User-Agent","Android")
                   //     .removeHeader("User-Agent")
//                        .removeHeader("If-Modified-Since")
//                        .addHeader("User-D", "")
//                        .addHeader("User-N", "")
//                        .addHeader("User-C", "")
                        .build();
                if (!SystemUtil.isNetworkConnected())
                {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();

                }

                Response response = chain.proceed(request);
//                if (SystemUtil.isNetworkConnected())
//                {
//                    int maxAge = 0;
//                    //有网络时，不缓存，保存最大的时长为0
//                    response.newBuilder()
//                            .header("Cache-Control", "max-age=" + maxAge)
//                            .removeHeader("Pragma")
//                            .build();
//                } else
//                {
//                    //无网络时，设置超时为一周
//                    int maxStale = 60 * 60 * 24 * 7;
//                    response.newBuilder()
//                            .header("Cache-Control", "only-if-cached, max-stale=" + maxStale)
//                            .removeHeader("Pragma")
//                            .build();
//                }

                return response;
            }
        };
        //设置缓存
        builder.addNetworkInterceptor(cacheInterceptor);
        builder.addInterceptor(cacheInterceptor);
        builder.cache(cache);
        //设置超时
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);
        okHttpClient = builder.build();
    }

    private String getCacheControl()
    {
        return SystemUtil.isNetworkConnected() ? CACHE_CONTROL_AGE : CACHE_CONTROL_CACHE;
    }

    public Observable<DailyListBean> fetchDailyListInfo()
    {
        return zhihuApiService.getDailyList();
    }

    public Call<DailyListBean> fetchDailyListInfos()
    {
        return zhihuApiService.getDailyLists();
    }

    public Observable<DailyBeforeListBean> fetchDailyBeforeListInfo(String date)
    {
        return zhihuApiService.getDailyBeforeList(date);
    }

    public Observable<ThemeListBean> fetchDailyThemeListInfo()
    {
        return zhihuApiService.getThemeList();
    }

    public Observable<ThemeChildListBean> fetchThemeChildListInfo(int id)
    {
        return zhihuApiService.getThemeChildList(id);
    }

    public Observable<SectionListBean> fetchSectionListInfo()
    {
        return zhihuApiService.getSectionList();
    }

    public Observable<SectionChildListBean> fetchSectionChildListInfo(int id)
    {
        return zhihuApiService.getSectionChildList(id);
    }

    public Observable<ZhihuDetailBean> fetchDetailInfo(int id)
    {
        return zhihuApiService.getDetailInfo(id);
    }

    public Observable<DetailExtraBean> fetchDetailExtraInfo(int id)
    {
        return zhihuApiService.getDetailExtraInfo(id);
    }

    public Observable<WelcomeBean> fetchWelcomeInfo(String res)
    {
        return zhihuApiService.getWelcomeInfo(res);
    }

    public Observable<CommentBean> fetchLongCommentInfo(int id)
    {
        return zhihuApiService.getLongCommentInfo(id);
    }

    public Observable<CommentBean> fetchShortCommentInfo(int id)
    {
        return zhihuApiService.getShortCommentInfo(id);
    }

    public Observable<HotListBean> fetchHotListInfo()
    {
        return zhihuApiService.getHotList();
    }

    public Observable<Map<String, List<NewsSummary>>> fetchYiNewsList(String newsId, int startPage)
    {

        return wangyiApiService.getNewsList(WangyiApis.OTHER_TYPE, newsId, startPage);
    }


    public Observable<Map<String, NewsDetail>> fetchYiNewsDetail(String postId)
    {
        return wangyiApiService.getNewDetail(postId);
    }

    public Observable<ResponseBody> fetchNewsBodyHtmlPhoto(String photoPath)
    {
        return wangyiApiService.getNewsBodyHtmlPhoto(photoPath);
    }
}
