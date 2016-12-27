package com.rr.tnews.network.result;

import com.google.gson.annotations.SerializedName;

/**
 * @author Roman
 * @since 1.0
 */

public class Result<T> {

    @SerializedName("resultCode")
    private String mResultCode;

    @SerializedName("payload")
    private T mPayload;

    public T getPayload() {
        return mPayload;
    }
}
