package com.ptu.tinynews.module.yi.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.ptu.tinynews.R;
import com.ptu.tinynews.app.App;
import com.ptu.tinynews.app.Constant;
import com.ptu.tinynews.base.SimpleFragment;
import com.ptu.tinynews.model.db.NewsChannelTable;
import com.ptu.tinynews.module.yi.adapter.WangyiMainAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2016/10/23.
 */

public class NewsFragment extends SimpleFragment
{

    @BindView(R.id.tabs)
    TabLayout mTabs;
    @BindView(R.id.add_channel_iv)
    ImageView mAddChannelIv;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    private List<NewsListFragment> mNewsFragmentList = new ArrayList<>();
    private List<String> channelNames = new ArrayList<>();
    private List<NewsChannelTable> newsChannels = new ArrayList<>();
    private List<String> channelId = new ArrayList<>();
    @Override
    protected int getLayoutId()
    {
        return R.layout.fragment_news;
    }

    @Override
    protected void initEventAndData()
    {
        //初始化数个fragment，应存入数据库中，这里仅为模拟测试
        String[] strings= App.getInstance().getApplicationContext().getResources()
                .getStringArray(R.array.news_channel_name);
        for (int i=0;i<5;i++)
        {
            channelNames.add(strings[i]);
        }

        String[] strings1=App.getInstance().getApplicationContext().getResources()
                .getStringArray(R.array.news_channel_id);
        for (int i=0;i<5;i++)
        {
            channelId.add(strings1[i]);
        }

        for (int i = 0; i < 5; i++)
        {
            NewsChannelTable entity = new NewsChannelTable(channelNames.get(i), channelId.get(i)
                    , Constant.getType(channelId.get(i)), i <= 5, i, i == 0);
            newsChannels.add(entity);
        }

        setNewsList(newsChannels, channelNames);
        mViewPager.setAdapter(new WangyiMainAdapter(getChildFragmentManager(), mNewsFragmentList, channelNames));

        mTabs.setupWithViewPager(mViewPager);
    }
    private void setNewsList(List<NewsChannelTable> newsChannels, List<String> channelNames)
    {
        mNewsFragmentList.clear();
        for (NewsChannelTable newsChannel : newsChannels)
        {
            NewsListFragment newsListFragment = createListFragments(newsChannel);
            mNewsFragmentList.add(newsListFragment);
            channelNames.add(newsChannel.getNewsChannelName());
        }
    }

    private NewsListFragment createListFragments(NewsChannelTable newsChannel)
    {
        NewsListFragment fragment = new NewsListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.NEWS_ID, newsChannel.getNewsChannelId());
        bundle.putString(Constant.NEWS_TYPE, newsChannel.getNewsChannelType());
        bundle.putInt(Constant.CHANNEL_POSITION, newsChannel.getNewsChannelIndex());
        fragment.setArguments(bundle);
        return fragment;
    }
}
