package com.ptu.tinynews.ui.zhihu.presenter;


import com.ptu.tinynews.base.RxPresenter;
import com.ptu.tinynews.contract.SectionChildContract;
import com.ptu.tinynews.model.bean.SectionChildListBean;
import com.ptu.tinynews.model.http.RetrofitHelper;
import com.ptu.tinynews.util.RxUtil;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by codeest on 16/8/28.
 */

public class SectionChildPresenter extends RxPresenter<SectionChildContract.View> implements SectionChildContract.Presenter{

    private RetrofitHelper mRetrofitHelper;
   // private RealmHelper mRealmHelper;

    @Inject
    public SectionChildPresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
      //  this.mRealmHelper = mRealmHelper;
    }

    @Override
    public void getThemeChildData(int id) {
        Subscription rxSubscription = mRetrofitHelper.fetchSectionChildListInfo(id)
                .compose(RxUtil.<SectionChildListBean>rxSchedulerHelper())
                .subscribe(new Action1<SectionChildListBean>() {
                    @Override
                    public void call(SectionChildListBean sectionChildListBean) {
                        mView.showContent(sectionChildListBean);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showMessage("加载数据失败");
                    }
                });
        addSubscribe(rxSubscription);
    }

    @Override
    public void insertReadToDB(int id){
    }
}
