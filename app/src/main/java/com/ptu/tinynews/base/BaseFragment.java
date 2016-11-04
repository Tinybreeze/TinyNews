package com.ptu.tinynews.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ptu.tinynews.app.App;
import com.ptu.tinynews.di.DaggerFragmentComponent;
import com.ptu.tinynews.di.FragmentComponent;
import com.ptu.tinynews.di.module.FragmentModule;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;


public abstract class BaseFragment<T extends BasePresenter> extends SupportFragment implements BaseView
{
    @Inject
    protected T mPresenter;
    protected View mView;
    protected Activity mActivity;
    protected Context mContext;
    private Unbinder mUnbinder;
    private boolean isInited = false;

    @Override
    public void onAttach(Context context)
    {
        this.mActivity = (Activity) context;
        mContext = context;
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        mView = inflater.inflate(getLayoutId(), null);
        initInject();
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.attachView(this);
        mUnbinder = ButterKnife.bind(this, view);
        if (savedInstanceState == null)
        {
            if (!isHidden())
            {
                isInited = true;
                initEventAndData();
            }
        } else
        {
            if (!isSupportHidden())
            {
                isInited = true;
                initEventAndData();
            }
        }
    }


    @Override
    public void onDestroy()
    {
        super.onDestroy();
        if (mPresenter != null)
        {
            mPresenter.detachView();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden)
    {
        super.onHiddenChanged(hidden);
        if (!isInited && !hidden)
        {
            isInited = true;
            initEventAndData();
        }
    }

    protected FragmentComponent getFragmentComponent()
    {
        return DaggerFragmentComponent.builder()
                .appComponent(App.getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    protected FragmentModule getFragmentModule()
    {
        return new FragmentModule(this);
    }


    protected abstract void initInject();

    protected abstract int getLayoutId();

    protected abstract void initEventAndData();
}
