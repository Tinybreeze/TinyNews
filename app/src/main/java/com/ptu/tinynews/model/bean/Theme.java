package com.ptu.tinynews.model.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/26.
 */
public class Theme {

    private int limit;

    private List<?> subscribed;

    private List<Others> others;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public List<?> getSubscribed() {
        return subscribed;
    }

    public void setSubscribed(List<?> subscribed) {
        this.subscribed = subscribed;
    }

    public List<Others> getOthers() {
        return others;
    }

    public void setOthers(List<Others> others) {
        this.others = others;
    }

}
