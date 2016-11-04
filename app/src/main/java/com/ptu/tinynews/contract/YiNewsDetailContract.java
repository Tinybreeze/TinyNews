package com.ptu.tinynews.contract;

import com.ptu.tinynews.base.BasePresenter;
import com.ptu.tinynews.base.BaseView;
import com.ptu.tinynews.model.bean.NewsDetail;

/**
 * Created by Administrator on 2016/11/1.
 */

public interface YiNewsDetailContract
{
    interface View extends BaseView
    {
        void showContent(NewsDetail newsDetail);
    }

    interface Presenter extends BasePresenter<View>
    {
        void getContent(String id);
    }
}
