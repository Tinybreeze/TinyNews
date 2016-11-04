package com.ptu.tinynews.ui.yi.activity;

import android.content.Intent;
import android.os.Bundle;

import com.ptu.tinynews.R;
import com.ptu.tinynews.app.Constant;
import com.ptu.tinynews.base.SimpleActivity;
import com.ptu.tinynews.util.ImageLoader;

import butterknife.BindView;
import uk.co.senab.photoview.PhotoView;

/**
 * Created by Administrator on 2016/11/3.
 */

public class NewsPhotoActivity extends SimpleActivity
{
    @BindView(R.id.photo_view)
    PhotoView mPhotoView;
    private String mImgSrc;
    @Override
    protected int getLayout()
    {
        return R.layout.fragment_news_photo_detail;
    }

    @Override
    protected void initEventAndData()
    {
        Intent intent =getIntent();
        Bundle bundle=intent.getExtras();
        mImgSrc= bundle.getString(Constant.PHOTO_DETAIL_IMGSRC);
        ImageLoader.load(mContext,mImgSrc,mPhotoView);
    }


}
