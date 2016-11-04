package com.ptu.tinynews.contract;


import com.ptu.tinynews.base.BasePresenter;
import com.ptu.tinynews.base.BaseView;
import com.ptu.tinynews.model.bean.SectionListBean;

/**
 * Created by codeest on 16/8/12.
 */

public interface SectionContract
{

    interface View extends BaseView
    {

        void showContent(SectionListBean sectionListBean);

    }

    interface Presenter extends BasePresenter<View>
    {

        void getSectionData();
    }
}
