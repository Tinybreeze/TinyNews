package com.ptu.tinynews.module.yi.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ptu.tinynews.R;
import com.ptu.tinynews.app.Constant;
import com.ptu.tinynews.base.BaseFragment;
import com.ptu.tinynews.contract.YiNewsListContract;
import com.ptu.tinynews.model.bean.NewsSummary;
import com.ptu.tinynews.module.yi.activity.NewsDetailActivity;
import com.ptu.tinynews.module.yi.activity.NewsPhotoActivity;
import com.ptu.tinynews.module.yi.adapter.DividerItemDecoration;
import com.ptu.tinynews.module.yi.adapter.NewsListAdapter;
import com.ptu.tinynews.module.yi.presenter.NewsListPresenter;
import com.ptu.tinynews.util.SnackbarUtil;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2016/10/24.
 */

public class NewsListFragment extends BaseFragment<NewsListPresenter> implements YiNewsListContract.View
{
    @BindView(R.id.news_rv)
    RecyclerView mNewsRv;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.empty_view)
    TextView mEmptyView;
    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;
    private String newsChannelId;
    private NewsListAdapter adapter;

    @Override
    protected void initInject()
    {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.fragment_news_list;
    }

    @Override
    protected void initEventAndData()
    {
        //获取到频道ID
        Bundle bundle = this.getArguments();
        newsChannelId = bundle.getString(Constant.NEWS_ID);


        adapter = new NewsListAdapter(mActivity);
        mNewsRv.setHasFixedSize(true);
        mNewsRv.setLayoutManager(new LinearLayoutManager(mActivity));
        mNewsRv.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));
        mNewsRv.setItemAnimator(new DefaultItemAnimator());
        mNewsRv.setAdapter(adapter);

        adapter.setOnItemClickListener(new NewsListAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(View view, int position, boolean isPhoto)
            {
                if (isPhoto)
                {
                    Intent intent = new Intent(mActivity, NewsPhotoActivity.class);
                    intent.putExtra(Constant.PHOTO_DETAIL_IMGSRC, adapter.getItemBean(position).getAds().get(0).getImgsrc());
                    Log.e("tiny ", "imgurl " + adapter.getItemBean(position).getAds().get(0).getImgsrc());
                    startActivity(intent);
                } else
                {
                    Intent intent = new Intent(mActivity, NewsDetailActivity.class);
                    intent.putExtra(Constant.NEWS_POST_ID, adapter.getItemBean(position).getPostid());
                    intent.putExtra(Constant.NEWS_IMG_RES, adapter.getItemBean(position).getImgsrc());
                    startActivity(intent);
//                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(), view.findViewById(R.id.news_summary_photo_iv), "photos");
//                        getActivity().startActivity(intent, options.toBundle());
//                    } else {
//                        //让新的Activity从一个小的范围扩大到全屏
//                        ActivityOptionsCompat options = ActivityOptionsCompat.makeScaleUpAnimation(view, view.getWidth()/* / 2*/, view.getHeight()/* / 2*/, 0, 0);
//                        ActivityCompat.startActivity(getActivity(), intent, options.toBundle());
//                    }

                }
            }
        });

        //下拉更多监听事件
        mNewsRv.addOnScrollListener(new RecyclerView.OnScrollListener()
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

                if (visibleItemCount > 0 && newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItemPosition >= totalItemCount - 1)
                {
                    mPresenter.getMoreData(newsChannelId);
                    //显示加载更多的布局
                    // mNewsListAdapter.showFooter();
                    //recyclerView.scrollToPosition(adapter.getItemCount() - 1);
                }
            }
        });
        //下拉刷新
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                mPresenter.getLastData(newsChannelId);
            }
        });
        //获取首页数据

        mPresenter.getLastData(newsChannelId);
    }


    @Override
    public void showNewsListLast(List<NewsSummary> info)
    {
        if (mSwipeRefreshLayout.isRefreshing())
        {
            mSwipeRefreshLayout.setRefreshing(false);
        }
        adapter.addLast(info);
    }

    @Override
    public void showNewsListMore(List<NewsSummary> info)
    {
        if (mSwipeRefreshLayout.isRefreshing())
        {
            mSwipeRefreshLayout.setRefreshing(false);
        }
        adapter.addMore(info);
    }

    @Override
    public void showMessage(String msg)
    {
        if (mSwipeRefreshLayout.isRefreshing())
        {
            mSwipeRefreshLayout.setRefreshing(false);
        }
        SnackbarUtil.show(mNewsRv, msg);
    }


}
