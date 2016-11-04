package com.ptu.tinynews.base;

/**
 * Created by Administrator on 2016/10/4.
 */
public interface BasePresenter<T extends BaseView>
{
    void attachView(T view);
    void detachView();
}
