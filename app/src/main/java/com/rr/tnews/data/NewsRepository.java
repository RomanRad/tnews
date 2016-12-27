package com.rr.tnews.data;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.rr.tnews.data.local.NewsLocalDataSource;
import com.rr.tnews.data.remote.NewsRemoteDataSource;
import com.rr.tnews.model.News;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Roman
 * @since 1.0
 */

public class NewsRepository implements NewsDataSource {

    private NewsDataSource mNewsLocalDataSource;
    private NewsDataSource mNewsRemoteDataSource;

    private Map<Long, News> mNews;

    public NewsRepository(Context context) {
        mNews = new HashMap<>();
        mNewsLocalDataSource = new NewsLocalDataSource(context);
        mNewsRemoteDataSource = new NewsRemoteDataSource();
        loadNews(mNewsLocalDataSource, null, true);
    }

    @Override
    public void getNews(LoadCallback<List<News>> callback) {
        if (mNews.isEmpty()) {
            loadNews(mNewsRemoteDataSource, callback, true);
        } else {
            setupNews(callback);
        }
    }

    @Override
    public void getNewsContent(Long newsId, final LoadCallback<News> callback) {
        News news = mNews.get(newsId);
        if (TextUtils.isEmpty(news.getContent())) {
            mNewsRemoteDataSource.getNewsContent(newsId, new LoadCallback<News>() {
                @Override
                public void onDataLoaded(News data) {
                    updateNews(data);
                    mNews.put(data.getId(), data);
                    callback.onDataLoaded(data);
                }

                @Override
                public void onLoadingFailed() {
                    callback.onLoadingFailed();
                }
            });
        } else {
            callback.onDataLoaded(news);
        }
    }

    @Override
    public void saveNews(List<News> news) {
        mNewsLocalDataSource.saveNews(news);
    }

    @Override
    public void updateNews(News data) {
        mNewsLocalDataSource.updateNews(data);
    }

    @Override
    public void dropData() {
        mNews.clear();
        mNewsLocalDataSource.dropData();
    }

    private void loadNews(NewsDataSource newsDataSource, @Nullable final LoadCallback<List<News>> callback, final boolean forceUpdate) {
        newsDataSource.getNews(new LoadCallback<List<News>>() {
            @Override
            public void onDataLoaded(List<News> data) {
                processNews(forceUpdate, data);
                if (callback != null) {
                    setupNews(callback);
                }
            }

            @Override
            public void onLoadingFailed() {
                if (callback != null) {
                    callback.onLoadingFailed();
                }
            }
        });
    }

    private void processNews(boolean forceUpdate, List<News> news) {
        if (forceUpdate) {
            saveNews(news);
        }
        for (News item : news) {
            mNews.put(item.getId(), item);
        }
    }

    private void setupNews(LoadCallback<List<News>> callback) {
        List<News> result = new ArrayList<>(mNews.values());
        Collections.sort(result);
        callback.onDataLoaded(result);
    }
}
