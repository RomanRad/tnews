package com.rr.tnews.data.remote;

import com.rr.tnews.data.LoadCallback;
import com.rr.tnews.data.NewsDataSource;
import com.rr.tnews.model.News;
import com.rr.tnews.network.NewsApi;
import com.rr.tnews.network.model.NewsDetails;
import com.rr.tnews.network.result.NewsDetailsResult;
import com.rr.tnews.network.result.NewsResult;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Roman
 * @since 1.0
 */

public class NewsRemoteDataSource implements NewsDataSource {

    private static final String BASE_URL = "https://api.tinkoff.ru/v1/";

    private NewsApi mNewsApi;

    public NewsRemoteDataSource() {
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).
                build();

        mNewsApi = retrofit.create(NewsApi.class);
    }

    @Override
    public void getNews(LoadCallback<List<News>> callback) {
        mNewsApi.getNews().enqueue(new CallbackAdapter<NewsResult, List<News>>(callback) {
            @Override
            protected List<News> processResponse(Response<NewsResult> response) {
                NewsResult result = response.body();
                List<News> data = new ArrayList<>();
                for (com.rr.tnews.network.model.News news : result.getPayload()) {
                    data.add(new News(news.getId(), news.getText(), news.getPublicationDate(), news.getBankInfoTypeId()));
                }
                return data;
            }
        });
    }

    @Override
    public void getNewsContent(Long newsId, final LoadCallback<News> callback) {
        mNewsApi.getNewsContent(newsId).enqueue(new CallbackAdapter<NewsDetailsResult, News>(callback) {
            @Override
            protected News processResponse(Response<NewsDetailsResult> response) {
                NewsDetails result = response.body().getPayload();
                com.rr.tnews.network.model.News news = result.getNews();
                return new News(news.getId(), news.getText(), news.getPublicationDate(), news.getBankInfoTypeId(), result.getContent());
            }
        });
    }

    @Override
    public void saveNews(List<News> news) {
    }

    @Override
    public void updateNews(News news) {
    }

    @Override
    public void dropData() {
    }

    private static abstract class CallbackAdapter<T, V> implements Callback<T> {

        private LoadCallback<V> mCallback;

        private CallbackAdapter(LoadCallback<V> callback) {
            mCallback = callback;
        }

        @Override
        public void onResponse(Call<T> call, Response<T> response) {
            mCallback.onDataLoaded(processResponse(response));
        }

        @Override
        public void onFailure(Call<T> call, Throwable throwable) {
            mCallback.onLoadingFailed();
        }

        protected abstract V processResponse(Response<T> response);
    }
}
