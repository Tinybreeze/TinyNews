package com.ptu.tinynews.contract;

import com.ptu.tinynews.base.BasePresenter;
import com.ptu.tinynews.base.BaseView;
import com.ptu.tinynews.model.bean.ZhihuDetailBean;

/**
 * Created by codeest on 16/8/13.
 */

public interface ZhihuDetailContract
{

    interface View extends BaseView {

        void showContent(ZhihuDetailBean zhihuDetailBean);
    }

    interface  Presenter extends BasePresenter<View>
    {

        void getDetailData(int id);
    }
}
