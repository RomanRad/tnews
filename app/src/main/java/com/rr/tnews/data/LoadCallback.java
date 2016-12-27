package com.rr.tnews.data;

/**
 * @author Roman
 * @since 1.0
 */

public interface LoadCallback<T> {

    void onDataLoaded(T data);

    void onLoadingFailed();
}
