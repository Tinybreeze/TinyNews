package com.ptu.tinynews.ui.main.presenter;

import com.ptu.tinynews.base.RxPresenter;
import com.ptu.tinynews.model.http.RetrofitHelper;
import com.ptu.tinynews.contract.MainContract;

import javax.inject.Inject;

/**
 * Created by Administrator on 2016/10/19.
 */

public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter
{
    private RetrofitHelper mRetrofitHelper;

    @Inject
    public MainPresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
    }


}
