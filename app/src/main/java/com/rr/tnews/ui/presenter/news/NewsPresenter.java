package com.rr.tnews.ui.presenter.news;

import com.rr.tnews.data.LoadCallback;
import com.rr.tnews.data.NewsDataSource;
import com.rr.tnews.model.News;
import com.rr.tnews.ui.presenter.BasePresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * @author Roman Radko
 * @since 1.0
 */

public class NewsPresenter extends BasePresenter<NewsContract.View> implements NewsContract.Presenter {

    private NewsDataSource mNewsDataSource;

    @Inject
    public NewsPresenter(NewsContract.View view, NewsDataSource newsDataSource) {
        super(view);
        mNewsDataSource = newsDataSource;
    }

    @Override
    public void loadNews(boolean forceUpdate) {
        getView().setLoadingIndicator(true);

        if (forceUpdate) {
            mNewsDataSource.dropData();
        }
        mNewsDataSource.getNews(new LoadCallback<List<News>>() {
            @Override
            public void onDataLoaded(List<News> data) {
                if (checkView()) {
                    NewsContract.View view = getView();
                    view.setLoadingIndicator(false);
                    if (data.isEmpty()) {
                        view.showNoNews();
                    } else {
                        view.showNews(data);
                    }
                }
            }

            @Override
            public void onLoadingFailed() {
                if (checkView()) {
                    getView().showLoadingNewsError();
                }
            }
        });
    }
}
