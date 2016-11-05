package com.ptu.tinynews.module.zhihu.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ptu.tinynews.R;
import com.ptu.tinynews.base.BaseFragment;
import com.ptu.tinynews.model.bean.DailyBeforeListBean;
import com.ptu.tinynews.model.bean.DailyListBean;
import com.ptu.tinynews.contract.ZhihuDailyContract;
import com.ptu.tinynews.module.zhihu.activity.ZhihuDetailActivity;
import com.ptu.tinynews.module.zhihu.adapter.DailyAdapter;
import com.ptu.tinynews.module.zhihu.presenter.DailyPresenter;
import com.ptu.tinynews.util.DateUtil;
import com.ptu.tinynews.util.SnackbarUtil;

import butterknife.BindView;

/**
 * Created by Administrator on 2016/10/4.
 */
public class DailyFragment extends BaseFragment<DailyPresenter> implements ZhihuDailyContract.View
{
    @BindView(R.id.refreshLayout)
     SwipeRefreshLayout refreshLayout;
    //文章列表
    @BindView(R.id.articleList)
     RecyclerView recyclerView;

    private DailyAdapter adapter;
    String currentDate;
    private boolean mIsAllLoaded;
    @Override
    protected void initInject()
    {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.activity_article_list;
    }

    @Override
    protected void initEventAndData()
    {
        currentDate = DateUtil.getTomorrowDate();
        adapter = new DailyAdapter(mActivity);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(adapter);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                if (currentDate.equals(DateUtil.getTomorrowDate()))
                {
                    mPresenter.getDailyData();
                }else {
                    showMessage("已经是最新文章啦");
                }
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState)
            {
                super.onScrollStateChanged(recyclerView, newState);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();

                int lastVisibleItemPosition = ((LinearLayoutManager) layoutManager)
                        .findLastVisibleItemPosition();
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();

                if (!mIsAllLoaded && visibleItemCount > 0 && newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItemPosition >= totalItemCount - 1) {
                    mPresenter.getBeforeData(currentDate);
                   //显示加载更多的布局
                    // mNewsListAdapter.showFooter();
                    recyclerView.scrollToPosition(adapter.getItemCount() - 1);
                }
            }
        });


        //RecyclerView item 的listener
        adapter.setOnItemClickListener(new DailyAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(int position, View view)
            {
                Intent intent = new Intent(mActivity, ZhihuDetailActivity.class);
                Bundle bundle = new Bundle();

                bundle.putInt("ID",adapter.getItem(position).getId() );
                intent.putExtras(bundle);
                mActivity.startActivity(intent);
            }
        });

//
//        //滑动到底部事件监听
//        adapter.setSlideToTheBottomListener(new OnSlideToTheBottomListener()
//        {
//            @Override
//            public void onSlideToTheBottom()
//            {
//                mPresenter.getBeforeData(currentDate);
//            }
//        });
//        //首次获取数据
        mPresenter.getDailyData();
    }

    @Override
    public void showMessage(String msg)
    {
        if (refreshLayout.isRefreshing())
        {
            refreshLayout.setRefreshing(false);
        }
        SnackbarUtil.show(recyclerView,msg);
    }


    @Override
    public void showContent(DailyListBean info)
    {
        if (refreshLayout.isRefreshing())
        {
            refreshLayout.setRefreshing(false);
        }
        adapter.addDailyDate(info);
    }

    @Override
    public void showMoreContent(String data, DailyBeforeListBean info)
    {
        currentDate = String.valueOf(Integer.valueOf(info.getDate()));
        adapter.addDailyBeforeDate(info);
    }

    @Override
    public void showProgress()
    {

    }

    @Override
    public void doInterval(int i)
    {

    }

}
