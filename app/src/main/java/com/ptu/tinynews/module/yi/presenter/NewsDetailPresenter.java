package com.ptu.tinynews.module.yi.presenter;

import com.ptu.tinynews.base.RxPresenter;
import com.ptu.tinynews.model.bean.NewsDetail;
import com.ptu.tinynews.model.http.RetrofitHelper;
import com.ptu.tinynews.contract.YiNewsDetailContract;
import com.ptu.tinynews.util.RxUtil;

import java.util.Map;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Administrator on 2016/11/1.
 */

public class NewsDetailPresenter extends RxPresenter<YiNewsDetailContract.View> implements YiNewsDetailContract.Presenter
{
    RetrofitHelper mRetrofitHelper;
    @Inject
    public NewsDetailPresenter(RetrofitHelper mRetrofitHelper)
    {
        this.mRetrofitHelper = mRetrofitHelper;
    }
    @Override
    public void getContent(final String id)
    {
        Subscription subscription =mRetrofitHelper.fetchYiNewsDetail(id)
                .map(new Func1<Map<String,NewsDetail>, NewsDetail>()
               {
                   @Override
                   public NewsDetail call(Map<String, NewsDetail> stringNewsDetailMap)
                   {
                       return stringNewsDetailMap.get(id);
                   }
               }) .compose(RxUtil.<NewsDetail>rxSchedulerHelper())
                .subscribe(new Action1<NewsDetail>()
                {
                    @Override
                    public void call(NewsDetail newsDetail)
                    {
                        mView.showContent(newsDetail);
                    }
                }, new Action1<Throwable>()
                {
                    @Override
                    public void call(Throwable throwable)
                    {
                        mView.showMessage("网络连接错误，请重试");
                    }
                });
        addSubscribe(subscription);
    }
}
