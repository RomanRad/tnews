package com.rr.tnews.ui.presenter.details;

import com.rr.tnews.data.LoadCallback;
import com.rr.tnews.data.NewsDataSource;
import com.rr.tnews.model.News;
import com.rr.tnews.ui.presenter.BasePresenter;

import javax.inject.Inject;

/**
 * @author Roman Radko
 * @since 1.0
 */

public class NewsDetailsPresenter extends BasePresenter<NewsDetailsContract.View> implements NewsDetailsContract.Presenter {

    private NewsDataSource mNewsDataSource;

    @Inject
    public NewsDetailsPresenter(NewsDetailsContract.View view, NewsDataSource newsDataSource) {
        super(view);
        mNewsDataSource = newsDataSource;
    }

    @Override
    public void loadNewsContent(Long newsId) {
        getView().setLoadingIndicator(true);
        mNewsDataSource.getNewsContent(newsId, new LoadCallback<News>() {
            @Override
            public void onDataLoaded(News data) {
                if(checkView()) {
                    getView().setLoadingIndicator(false);
                    getView().showNews(data);
                }
            }

            @Override
            public void onLoadingFailed() {
                if(checkView()) {
                    getView().showLoadingNewsError();
                }
            }
        });
    }
}
