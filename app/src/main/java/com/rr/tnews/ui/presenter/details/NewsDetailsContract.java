package com.rr.tnews.ui.presenter.details;

import com.rr.tnews.model.News;

/**
 * @author Roman Radko
 * @since 1.0
 */

public interface NewsDetailsContract {
    interface View {

        void setLoadingIndicator(boolean active);

        void showNews(News news);

        void showLoadingNewsError();
    }

    interface Presenter {

        void loadNewsContent(Long newsId);
    }
}
