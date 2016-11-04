package com.ptu.tinynews.ui.yi.activity;


import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ptu.tinynews.R;
import com.ptu.tinynews.app.Constant;
import com.ptu.tinynews.base.BaseActivity;
import com.ptu.tinynews.model.bean.NewsDetail;
import com.ptu.tinynews.contract.YiNewsDetailContract;
import com.ptu.tinynews.ui.yi.presenter.NewsDetailPresenter;
import com.ptu.tinynews.util.HtmlUtil;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import net.opacapp.multilinecollapsingtoolbar.CollapsingToolbarLayout;

import java.util.List;

import butterknife.BindView;


/**
 * Created by Administrator on 2016/11/1.
 */

public class NewsDetailActivity extends BaseActivity<NewsDetailPresenter> implements YiNewsDetailContract.View
{


    @BindView(R.id.detail_bar_image)
    ImageView mDetailBarImage;
    @BindView(R.id.detail_bar_copyright)
    TextView mDetailBarCopyright;
    @BindView(R.id.view_toolbar)
    Toolbar mViewToolbar;
    @BindView(R.id.clp_toolbar)
    CollapsingToolbarLayout mClpToolbar;
    @BindView(R.id.app_bar)
    AppBarLayout mAppBar;
    @BindView(R.id.wv_detail_content)
    WebView wvDetailContent;
    @BindView(R.id.nsv_scroller)
    NestedScrollView nsvScroller;
    private String mShareLink;
    private String mNewsTitle;

    @Override
    protected int getLayout()
    {
        return R.layout.activity_news_detail;
    }

    @Override
    protected void initInject()
    {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initEventAndData()
    {
        String postId = getIntent().getStringExtra(Constant.NEWS_POST_ID);

        WebSettings settings = wvDetailContent.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(true);
        wvDetailContent.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        mPresenter.getContent(postId);
    }

    @Override
    public void showContent(NewsDetail newsDetail)
    {
      //  mShareLink = newsDetail.getShareLink();
        mNewsTitle = newsDetail.getTitle();
        String newsSource = newsDetail.getSource();
        String newsTime = newsDetail.getPtime();
        String newsBody = newsDetail.getBody();
        String NewsImgSrc = getImgSrcs(newsDetail);
        setToolBar(mViewToolbar, mNewsTitle);
        setNewsDetailPhotoIv(NewsImgSrc);
        setNewsDetailBodyTv(newsDetail, newsBody);
    }

    private void setNewsDetailPhotoIv(String imgSrc)
    {
        Glide.with(this).load(imgSrc).asBitmap()
                .placeholder(R.mipmap.load)
                .format(DecodeFormat.PREFER_ARGB_8888)
                .error(R.mipmap.ic_load_fail)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mDetailBarImage);
    }

    //获取到图集
    private String getImgSrcs(NewsDetail newsDetail)
    {
        List<String> imgSrcs = newsDetail.getImgList();
       String imgSrc=null;
        if (imgSrcs != null && imgSrcs.size() > 0)
        {
            imgSrc = imgSrcs.get(0);
        } else
        {
            imgSrc = getIntent().getStringExtra(Constant.NEWS_IMG_RES);
        }
        return imgSrc;
    }

    private void setNewsDetailBodyTv(final NewsDetail newsDetail, final String newsBody)
    {
        mClpToolbar.setTitle(newsDetail.getTitle());
        mDetailBarCopyright.setText(newsDetail.getSource());
        setBody(newsDetail, newsBody);
    }

    private void setBody(NewsDetail newsDetail, String newsBody)
    {
        wvDetailContent.loadData(newsBody, HtmlUtil.MIME_TYPE, HtmlUtil.ENCODING );
    }

    @Override
    public void showMessage(String msg)
    {

    }


}
