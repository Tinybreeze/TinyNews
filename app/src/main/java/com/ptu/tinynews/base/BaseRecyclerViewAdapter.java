/*
 * Copyright (c) 2016 咖枯 <kaku201313@163.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.ptu.tinynews.base;

import android.support.annotation.AnimRes;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/9/20.
 */
public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    public static final int TYPE_ITEM = 0;
    public static final int TYPE_FOOTER = 1;
    protected int mLastPosition = -1;
    protected boolean mIsShowFooter;
    protected List<T> mList;
    protected OnItemClickListener mOnItemClickListener;

    public BaseRecyclerViewAdapter(List<T> list)
    {
        mList = list;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        if (getItemViewType(position) == TYPE_FOOTER)
        {
            if (layoutParams != null)
            {
                if (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams)
                {
                    StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) holder.itemView
                            .getLayoutParams();
                    params.setFullSpan(true);
                }
            }
        }
    }

    protected View getView(ViewGroup parent, int layoutId)
    {
        return LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
    }


    protected void setItemAppearAnimation(RecyclerView.ViewHolder holder, int position, @AnimRes int type)
    {
        if (position > mLastPosition/* && !isFooterPosition(position)*/)
        {
            Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), type);
            holder.itemView.startAnimation(animation);
            mLastPosition = position;
        }
    }


    public void add(int position, T item)
    {
        mList.add(position, item);
        notifyItemInserted(position);
    }

    public void addMore(List<T> data)
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

    public List<T> getList()
    {
        return mList;
    }

    public void setList(List<T> items)
    {
        mList = items;
    }

    protected boolean isFooterPosition(int position)
    {
        return (getItemCount() - 1) == position;
    }

    public void showFooter()
    {
        mIsShowFooter = true;
        notifyItemInserted(getItemCount());
    }

    public void hideFooter()
    {
        mIsShowFooter = false;
        notifyItemRemoved(getItemCount());
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener)
    {
        mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener
    {
        void onItemClick(View view, int position);
    }

    protected class FooterViewHolder extends RecyclerView.ViewHolder
    {

        public FooterViewHolder(View itemView)
        {
            super(itemView);
        }
    }
}
