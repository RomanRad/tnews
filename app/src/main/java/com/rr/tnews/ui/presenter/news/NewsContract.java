package com.rr.tnews.ui.presenter.news;

import com.rr.tnews.model.News;

import java.util.List;

/**
 * @author Roman Radko
 * @since 1.0
 */

public interface NewsContract {

    interface View {

        void setLoadingIndicator(boolean active);

        void showNews(List<News> news);

        void showLoadingNewsError();

        void showNoNews();
    }

    interface Presenter {

        void loadNews(boolean forceUpdate);
    }
}
