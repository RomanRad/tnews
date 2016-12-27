package com.rr.tnews.model;

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
}
