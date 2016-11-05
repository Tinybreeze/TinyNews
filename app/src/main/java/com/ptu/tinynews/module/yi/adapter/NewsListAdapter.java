package com.ptu.tinynews.module.yi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ptu.tinynews.R;
import com.ptu.tinynews.app.App;
import com.ptu.tinynews.model.bean.NewsSummary;
import com.ptu.tinynews.util.ImageLoader;
import com.ptu.tinynews.util.SystemUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/10/26.
 */

public class NewsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    public static final int TOP = 0;
    public static final int ITEM = 1;
    public static final int FOOT = 2;
    private final LayoutInflater inflater;
    private Context mContext;
    private List<NewsSummary> mList = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener;

    public NewsListAdapter(Context mContext)
    {
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getItemViewType(int position)
    {
        if (mList.get(position).getHasHead() == 1)
        {
            return TOP;
        } else
        {
            return ITEM;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        switch (viewType)
        {
            case TOP:
                final PhotoViewHolder photoViewHolder = new PhotoViewHolder(inflater.inflate(R.layout.item_news_photo, parent, false));
                setItemOnClickEvent(photoViewHolder, true);
                return photoViewHolder;
            case ITEM:
                final ItemViewHolder itemViewHolder = new ItemViewHolder(inflater.inflate(R.layout.item_news, parent, false));
                setItemOnClickEvent(itemViewHolder, false);
                return itemViewHolder;
            default:
                break;
        }

        return new FooterViewHolder(inflater.inflate(R.layout.item_news_footer, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position)
    {
        if (holder instanceof ItemViewHolder)
        {
            setItemValues((ItemViewHolder) holder, position);
            setItemOnClickEvent(holder, false);

        } else if (holder instanceof PhotoViewHolder)
        {
            setPhotoItemValues((PhotoViewHolder) holder, position);
           setItemOnClickEvent(holder,true);
        }
    }

    @Override
    public int getItemCount()
    {
        return mList.size();
    }

    public NewsSummary getItemBean(int position)
    {
        return mList.get(position);
    }

    //新闻条目数据显示
    private void setItemValues(ItemViewHolder holder, int position)
    {
        NewsSummary newsSummary = mList.get(position);
        String imgSrc = newsSummary.getImgsrc();

        holder.mNewsSummaryTitleTv.setText(newsSummary.getTitle());
        holder.mNewsSummaryPtimeTv.setText(newsSummary.getPtime());
        holder.mNewsSummaryDigestTv.setText(newsSummary.getDigest());
        ImageLoader.load(mContext, imgSrc, holder.mNewsSummaryPhotoIv);
    }

    //显示多图item数据
    private void setPhotoItemValues(PhotoViewHolder holder, int position)
    {
        NewsSummary newsSummary = mList.get(position);

        holder.mNewsSummaryTitleTv.setText(newsSummary.getTitle());
        holder.mNewsSummaryPtimeTv.setText(newsSummary.getPtime());

        setImageView(holder, newsSummary);
    }


    private void setImageView(PhotoViewHolder holder, NewsSummary newsSummary)
    {
        int PhotoThreeHeight = SystemUtil.dpTopx(mContext, 90);
        int PhotoTwoHeight = SystemUtil.dpTopx(mContext, 120);
        int PhotoOneHeight = SystemUtil.dpTopx(mContext, 150);

        String imgSrcLeft = null;
        String imgSrcMiddle = null;
        String imgSrcRight = null;

        ViewGroup.LayoutParams layoutParams = holder.mNewsSummaryPhotoIvGroup.getLayoutParams();

        if (newsSummary.getAds() != null)
        {
            List<NewsSummary.AdsBean> adsBeanList = newsSummary.getAds();
            int size = adsBeanList.size();
            if (size >= 3)
            {
                imgSrcLeft = adsBeanList.get(0).getImgsrc();
                imgSrcMiddle = adsBeanList.get(1).getImgsrc();
                imgSrcRight = adsBeanList.get(2).getImgsrc();

                layoutParams.height = PhotoThreeHeight;

                holder.mNewsSummaryTitleTv.setText(App.getInstance().getApplicationContext()
                        .getString(R.string.photo_collections, adsBeanList.get(0).getTitle()));
            } else if (size >= 2)
            {
                imgSrcLeft = adsBeanList.get(0).getImgsrc();
                imgSrcMiddle = adsBeanList.get(1).getImgsrc();

                layoutParams.height = PhotoTwoHeight;
            } else if (size >= 1)
            {
                imgSrcLeft = adsBeanList.get(0).getImgsrc();

                layoutParams.height = PhotoOneHeight;
            }
        } else if (newsSummary.getImgextra() != null)
        {
            int size = newsSummary.getImgextra().size();
            if (size >= 3)
            {
                imgSrcLeft = newsSummary.getImgextra().get(0).getImgsrc();
                imgSrcMiddle = newsSummary.getImgextra().get(1).getImgsrc();
                imgSrcRight = newsSummary.getImgextra().get(2).getImgsrc();

                layoutParams.height = PhotoThreeHeight;
            } else if (size >= 2)
            {
                imgSrcLeft = newsSummary.getImgextra().get(0).getImgsrc();
                imgSrcMiddle = newsSummary.getImgextra().get(1).getImgsrc();

                layoutParams.height = PhotoTwoHeight;
            } else if (size >= 1)
            {
                imgSrcLeft = newsSummary.getImgextra().get(0).getImgsrc();

                layoutParams.height = PhotoOneHeight;
            }
        } else
        {
            imgSrcLeft = newsSummary.getImgsrc();

            layoutParams.height = PhotoOneHeight;
        }

        setPhotoImageView(holder, imgSrcLeft, imgSrcMiddle, imgSrcRight);
        holder.mNewsSummaryPhotoIvGroup.setLayoutParams(layoutParams);
    }

    private void setPhotoImageView(PhotoViewHolder holder, String imgSrcLeft, String imgSrcMiddle, String imgSrcRight)
    {
        if (imgSrcLeft != null)
        {
            showAndSetPhoto(holder.mNewsSummaryPhotoIvLeft, imgSrcLeft);
        } else
        {
            hidePhoto(holder.mNewsSummaryPhotoIvLeft);
        }

        if (imgSrcMiddle != null)
        {
            showAndSetPhoto(holder.mNewsSummaryPhotoIvMiddle, imgSrcMiddle);
        } else
        {
            hidePhoto(holder.mNewsSummaryPhotoIvMiddle);
        }

        if (imgSrcRight != null)
        {
            showAndSetPhoto(holder.mNewsSummaryPhotoIvRight, imgSrcRight);
        } else
        {
            hidePhoto(holder.mNewsSummaryPhotoIvRight);
        }
    }

    private void showAndSetPhoto(ImageView imageView, String imgSrc)
    {
        imageView.setVisibility(View.VISIBLE);
        ImageLoader.load(mContext, imgSrc, imageView);
    }

    private void hidePhoto(ImageView imageView)
    {
        imageView.setVisibility(View.GONE);
    }

    public void add(int position, NewsSummary item)
    {
        mList.add(position, item);
        notifyItemInserted(position);
    }

    public void addLast(List<NewsSummary> data)
    {
        mList = data;
        notifyDataSetChanged();
    }

    public void addMore(List<NewsSummary> data)
    {
        int startPosition = mList.size();
        mList.addAll(data);
        notifyItemRangeInserted(startPosition, mList.size());
    }

    public void delete(int position)
    {
        mList.remove(position);
        notifyItemRemoved(position);
    }

    private void setItemOnClickEvent(final RecyclerView.ViewHolder holder, final boolean isPhoto)
    {
        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (mOnItemClickListener != null)
                {
                    mOnItemClickListener.onItemClick(view, holder.getLayoutPosition(), isPhoto);
                }
            }
        });

    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener)
    {
        mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener
    {
        void onItemClick(View view, int position, boolean isPhoto);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.news_summary_photo_iv)
        ImageView mNewsSummaryPhotoIv;
        @BindView(R.id.news_summary_title_tv)
        TextView mNewsSummaryTitleTv;
        @BindView(R.id.news_summary_digest_tv)
        TextView mNewsSummaryDigestTv;
        @BindView(R.id.news_summary_ptime_tv)
        TextView mNewsSummaryPtimeTv;

        public ItemViewHolder(View view)
        {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public class PhotoViewHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.news_summary_title_tv)
        TextView mNewsSummaryTitleTv;
        @BindView(R.id.news_summary_photo_iv_group)
        LinearLayout mNewsSummaryPhotoIvGroup;
        @BindView(R.id.news_summary_photo_iv_left)
        ImageView mNewsSummaryPhotoIvLeft;
        @BindView(R.id.news_summary_photo_iv_middle)
        ImageView mNewsSummaryPhotoIvMiddle;
        @BindView(R.id.news_summary_photo_iv_right)
        ImageView mNewsSummaryPhotoIvRight;
        @BindView(R.id.news_summary_ptime_tv)
        TextView mNewsSummaryPtimeTv;

        public PhotoViewHolder(View view)
        {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    protected class FooterViewHolder extends RecyclerView.ViewHolder
    {

        public FooterViewHolder(View itemView)
        {
            super(itemView);
        }
    }
}
