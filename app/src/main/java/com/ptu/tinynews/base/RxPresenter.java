package com.ptu.tinynews.base;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2016/10/14.
 */

public class RxPresenter<T extends BaseView > implements BasePresenter<T>
{
    protected  T mView;
    protected CompositeSubscription mCompositeSubscription;
    @Override
    public void attachView(T view)
    {
        this.mView=view;
    }

    @Override
    public void detachView()
    {
        unSubscribe();
        this.mView=null;
    }
    protected void addSubscribe(Subscription subscription)
    {
        if (mCompositeSubscription == null)
        {
            mCompositeSubscription=new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }
    protected void unSubscribe()
    {
        if (mCompositeSubscription != null)
        {
            mCompositeSubscription.unsubscribe();
        }
    }
}
