package com.ptu.tinynews.module.zhihu.presenter;


import com.ptu.tinynews.base.RxPresenter;
import com.ptu.tinynews.contract.SectionContract;
import com.ptu.tinynews.model.bean.SectionListBean;
import com.ptu.tinynews.model.http.RetrofitHelper;
import com.ptu.tinynews.util.RxUtil;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by Administrator on 2016/10/19.
 */

public class SectionPresenter extends RxPresenter<SectionContract.View> implements SectionContract.Presenter{

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public SectionPresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
    }

    @Override
    public void getSectionData() {
        Subscription rxSubscription = mRetrofitHelper.fetchSectionListInfo()
                .compose(RxUtil.<SectionListBean>rxSchedulerHelper())
                .subscribe(new Action1<SectionListBean>() {
                    @Override
                    public void call(SectionListBean sectionListBean) {
                        mView.showContent(sectionListBean);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showMessage("数据加载失败ヽ(≧Д≦)ノ");
                    }
                });
        addSubscribe(rxSubscription);
    }
}
