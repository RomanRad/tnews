package com.rr.tnews.di.news;

import com.rr.tnews.ui.presenter.news.NewsContract;

import dagger.Module;
import dagger.Provides;

/**
 * @author Roman Radko
 * @since 1.0
 */

@Module
public class NewsPresenterModule {

    private NewsContract.View mView;

    public NewsPresenterModule(NewsContract.View view) {
        mView = view;
    }

    @Provides
    NewsContract.View provideNewsView() {
        return mView;
    }
}
