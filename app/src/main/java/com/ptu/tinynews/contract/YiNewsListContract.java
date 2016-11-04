package com.ptu.tinynews.contract;

import com.ptu.tinynews.base.BasePresenter;
import com.ptu.tinynews.base.BaseView;
import com.ptu.tinynews.model.bean.NewsSummary;

import java.util.List;

/**
 * Created by Administrator on 2016/10/23.
 */

public interface YiNewsListContract
{
    interface View extends BaseView
    {
        void showNewsListLast(List<NewsSummary> info);
        void showNewsListMore(List<NewsSummary> info);
    }

    interface Presenter extends BasePresenter<View>
    {
        void getLastData(String newsChannelId);
        void getMoreData(String newsChannelId);
    }
}
