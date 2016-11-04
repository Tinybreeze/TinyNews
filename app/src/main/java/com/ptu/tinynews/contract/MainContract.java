package com.ptu.tinynews.contract;

import com.ptu.tinynews.base.BasePresenter;
import com.ptu.tinynews.base.BaseView;

/**
 * Created by Administrator on 2016/10/19.
 */

public interface MainContract
{
    interface View extends BaseView
    {

    }

    interface Presenter extends BasePresenter<View>
    {

    }
}
