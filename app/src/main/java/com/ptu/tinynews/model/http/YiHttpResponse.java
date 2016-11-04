package com.ptu.tinynews.model.http;

/**
 * Created by Administrator on 2016/10/26.
 */

public class YiHttpResponse<T>
{
    private T newsChannelId;

    public T getNewsChannelId() {
        return newsChannelId;
    }

    public void setNewsChannelId(T newsChannelId) {
        this.newsChannelId = newsChannelId;
    }
}
