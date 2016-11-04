package com.ptu.tinynews.contract;


import com.ptu.tinynews.base.BasePresenter;
import com.ptu.tinynews.base.BaseView;
import com.ptu.tinynews.model.bean.SectionChildListBean;

/**
 * Created by codeest on 16/8/28.
 */

public interface SectionChildContract
{

    interface View extends BaseView
    {

        void showContent(SectionChildListBean sectionChildListBean);
    }

    interface Presenter extends BasePresenter<View>
    {

        void getThemeChildData(int id);

        void insertReadToDB(int id);
    }
}
