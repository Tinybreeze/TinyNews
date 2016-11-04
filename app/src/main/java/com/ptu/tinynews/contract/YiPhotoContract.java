package com.ptu.tinynews.contract;

import com.ptu.tinynews.base.BasePresenter;
import com.ptu.tinynews.base.BaseView;

/**
 * Created by Administrator on 2016/11/3.
 */

public interface YiPhotoContract
{
    interface View extends BaseView
    {
        void showContent();
    }

    interface Presenter extends BasePresenter<View>
    {
        void getPhoto();
    }
}
