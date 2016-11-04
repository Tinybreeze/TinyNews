package com.ptu.tinynews.ui.zhihu.presenter;


import com.ptu.tinynews.base.RxPresenter;
import com.ptu.tinynews.model.bean.DailyBeforeListBean;
import com.ptu.tinynews.model.bean.DailyListBean;
import com.ptu.tinynews.model.http.RetrofitHelper;
import com.ptu.tinynews.contract.ZhihuDailyContract;
import com.ptu.tinynews.util.RxUtil;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by Administrator on 2016/10/4.
 */
public class DailyPresenter extends RxPresenter<ZhihuDailyContract.View> implements ZhihuDailyContract.Presenter
{

    public RetrofitHelper mRetrofitHelper;
    //private RealmHelper mRealmHelper;

    @Inject
    public DailyPresenter(RetrofitHelper mRetrofitHelper)
    {
        this.mRetrofitHelper = mRetrofitHelper;
        // this.mRealmHelper = mRealmHelper;
    }


    /**
     * 获取最新的日报数据
     */
    @Override
    public void getDailyData()
    {
        Subscription subscription = mRetrofitHelper.fetchDailyListInfo()
                .compose(RxUtil.<DailyListBean>rxSchedulerHelper())
                .subscribe(new Action1<DailyListBean>()
                {
                    @Override
                    public void call(DailyListBean dailyListBean)
                    {
                        mView.showContent(dailyListBean);

                    }
                }, new Action1<Throwable>()
                {
                    @Override
                    public void call(Throwable throwable)
                    {
                        mView.showMessage("数据加载失败");
                    }
                });
        addSubscribe(subscription);
    }

    /**
     * 获取较早的日报数据
     *
     * @param data
     */
    @Override
    public void getBeforeData(final String data)
    {
        Subscription subscription = mRetrofitHelper.fetchDailyBeforeListInfo(data)
                .compose(RxUtil.<DailyBeforeListBean>rxSchedulerHelper())
                .subscribe(new Action1<DailyBeforeListBean>()
                {
                    @Override
                    public void call(DailyBeforeListBean dailyBeforeListBean)
                    {
                        int year = Integer.valueOf(dailyBeforeListBean.getDate().substring(0, 4));
                        int month = Integer.valueOf(dailyBeforeListBean.getDate().substring(4, 6));
                        int day = Integer.valueOf(dailyBeforeListBean.getDate().substring(6, 8));
                        mView.showMoreContent(String.format("%d年%d月%d日", year, month, day), dailyBeforeListBean);
                    }
                }, new Action1<Throwable>()
                {
                    @Override
                    public void call(Throwable throwable)
                    {
                        mView.showMessage("数据加载失败");
                    }
                });
        addSubscribe(subscription);
    }

    @Override
    public void startInterval()
    {

    }

    @Override
    public void stopInterval()
    {

    }

    @Override
    public void insertReadToDB(int i)
    {

    }
}
