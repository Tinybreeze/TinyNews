package com.ptu.tinynews.contract;

import com.ptu.tinynews.base.BasePresenter;
import com.ptu.tinynews.base.BaseView;
import com.ptu.tinynews.model.bean.DailyBeforeListBean;
import com.ptu.tinynews.model.bean.DailyListBean;

/**
 * Created by Administrator on 2016/10/4.
 */
public interface ZhihuDailyContract
{
    interface View extends BaseView
    {
        void showContent(DailyListBean info);
        void showMoreContent(String data, DailyBeforeListBean info);
        void showProgress();
        void doInterval(int i);
    }

    interface Presenter extends BasePresenter<View>
    {
        void getDailyData();
        void getBeforeData(String data);
        void startInterval();
        void stopInterval();
        void insertReadToDB(int i);
    }
}
