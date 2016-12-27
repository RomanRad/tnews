package com.rr.tnews.data;

import com.rr.tnews.model.News;

import java.util.List;

/**
 * @author Roman
 * @since 1.0
 */

public interface NewsDataSource {

    void getNews(LoadCallback<List<News>> callback);

    void getNewsContent(Long newsId, LoadCallback<News> callback);

    void saveNews(final List<News> news);

    void updateNews(News data);

    void dropData();
}
