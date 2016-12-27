package com.rr.tnews.network.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author Roman Radko
 * @since 1.0
 */

public class News {
    @SerializedName("id")
    private long mId;

    @SerializedName("text")
    private String mText;

    @SerializedName("publicationDate")
    private Date mPublicationDate;

    @SerializedName("bankInfoTypeId")
    private int mBankInfoTypeId;

    public long getId() {
        return mId;
    }

    public String getText() {
        return mText;
    }

    public int getBankInfoTypeId() {
        return mBankInfoTypeId;
    }

    public java.util.Date getPublicationDate() {
        return new java.util.Date(mPublicationDate.mMillis);
    }

    private class Date {
        @SerializedName("milliseconds")
        private long mMillis;
    }
}
