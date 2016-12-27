package com.rr.tnews.network.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author Roman
 * @since 1.0
 */

public class NewsDetails {

    @SerializedName("title")
    private News mNews;

    @SerializedName("content")
    private String mContent;

    public News getNews() {
        return mNews;
    }

    public String getContent() {
        return mContent;
    }
}
