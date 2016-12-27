package com.rr.tnews.network;

import com.rr.tnews.network.result.NewsDetailsResult;
import com.rr.tnews.network.result.NewsResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author Roman
 * @since 1.0
 */

public interface NewsApi {
    @GET("news")
    Call<NewsResult> getNews();

    @GET("news_content")
    Call<NewsDetailsResult> getNewsContent(@Query("id") Long id);
}
