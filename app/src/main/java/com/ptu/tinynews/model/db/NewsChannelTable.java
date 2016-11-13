package com.ptu.tinynews.model.db;

/**
 * Entity mapped to table NEWS_CHANNEL_TABLE.
 */
public class NewsChannelTable
{

    /** Not-null value. */
    private String newsChannelName;
    /** Not-null value. */
    private String newsChannelId;
    /** Not-null value. */
    private String newsChannelType;
    private boolean newsChannelSelect;
    private int newsChannelIndex;
    private Boolean newsChannelFixed;

    public NewsChannelTable() {
    }

    public NewsChannelTable(String newsChannelName) {
        this.newsChannelName = newsChannelName;
    }

    public NewsChannelTable(String newsChannelName, String newsChannelId, String newsChannelType, boolean newsChannelSelect, int newsChannelIndex, Boolean newsChannelFixed) {
        this.newsChannelName = newsChannelName;
        this.newsChannelId = newsChannelId;
        this.newsChannelType = newsChannelType;
        this.newsChannelSelect = newsChannelSelect;
        this.newsChannelIndex = newsChannelIndex;
        this.newsChannelFixed = newsChannelFixed;
    }

    /** Not-null value. */
    public String getNewsChannelName() {
        return newsChannelName;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setNewsChannelName(String newsChannelName) {
        this.newsChannelName = newsChannelName;
    }

    /** Not-null value. */
    public String getNewsChannelId() {
        return newsChannelId;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setNewsChannelId(String newsChannelId) {
        this.newsChannelId = newsChannelId;
    }

    /** Not-null value. */
    public String getNewsChannelType() {
        return newsChannelType;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setNewsChannelType(String newsChannelType) {
        this.newsChannelType = newsChannelType;
    }

    public boolean getNewsChannelSelect() {
        return newsChannelSelect;
    }

    public void setNewsChannelSelect(boolean newsChannelSelect) {
        this.newsChannelSelect = newsChannelSelect;
    }

    public int getNewsChannelIndex() {
        return newsChannelIndex;
    }

    public void setNewsChannelIndex(int newsChannelIndex) {
        this.newsChannelIndex = newsChannelIndex;
    }

    public Boolean getNewsChannelFixed() {
        return newsChannelFixed;
    }

    public void setNewsChannelFixed(Boolean newsChannelFixed) {
        this.newsChannelFixed = newsChannelFixed;
    }

}
