package com.ptu.tinynews.module.zhihu.activity;

import android.content.Intent;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;

import com.ptu.tinynews.R;
import com.ptu.tinynews.base.BaseActivity;
import com.ptu.tinynews.contract.ZhihuDetailContract;
import com.ptu.tinynews.model.bean.ZhihuDetailBean;
import com.ptu.tinynews.module.zhihu.presenter.ZhihuDetailPresenter;
import com.ptu.tinynews.util.HtmlUtil;
import com.ptu.tinynews.util.ImageLoader;
import com.ptu.tinynews.util.SnackbarUtil;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import net.opacapp.multilinecollapsingtoolbar.CollapsingToolbarLayout;

import butterknife.BindView;

/**
 * Created by codeest on 16/8/13.
 */

public class ZhihuDetailActivity extends BaseActivity<ZhihuDetailPresenter> implements ZhihuDetailContract.View
{

    @BindView(R.id.detail_bar_image)
    ImageView detailBarImage;
    @BindView(R.id.detail_bar_copyright)
    TextView detailBarCopyright;
    @BindView(R.id.view_toolbar)
    Toolbar viewToolbar;
    @BindView(R.id.clp_toolbar)
    CollapsingToolbarLayout clpToolbar;
    @BindView(R.id.wv_detail_content)
    WebView wvDetailContent;
    @BindView(R.id.nsv_scroller)
    NestedScrollView nsvScroller;
    int id = 0;
    String shareUrl;
    String imgUrl;
    boolean isImageShow = false;
    boolean isTransitionEnd = false;
    boolean isNotTransition = false;

    @Override
    protected void initInject()
    {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout()
    {
        return R.layout.activity_news_detail;
    }

    @Override
    protected void initEventAndData()
    {

        setToolBar(viewToolbar, "");
        Intent intent = getIntent();
        id = intent.getExtras().getInt("ID");
        isNotTransition = intent.getBooleanExtra("isNotTransition", false);

        WebSettings settings = wvDetailContent.getSettings();

        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(true);
        wvDetailContent.setWebViewClient(new WebViewClient()
        {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url)
            {
                view.loadUrl(url);
                return true;
            }
        });

        mPresenter.getDetailData(id);

    }

    @Override
    public void showContent(ZhihuDetailBean zhihuDetailBean)
    {
        imgUrl = zhihuDetailBean.getImage();
        shareUrl = zhihuDetailBean.getShare_url();

        ImageLoader.load(mContext, zhihuDetailBean.getImage(), detailBarImage);

        clpToolbar.setTitle(zhihuDetailBean.getTitle());
        detailBarCopyright.setText(zhihuDetailBean.getImage_source());
        String htmlData = HtmlUtil.createHtmlData(zhihuDetailBean.getBody(), zhihuDetailBean.getCss(), zhihuDetailBean.getJs());
        wvDetailContent.loadData(htmlData, HtmlUtil.MIME_TYPE, HtmlUtil.ENCODING);
    }


    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && wvDetailContent.canGoBack())
        {
            wvDetailContent.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void showMessage(String msg)
    {

        SnackbarUtil.showShort(getWindow().getDecorView(), msg);
    }

}
