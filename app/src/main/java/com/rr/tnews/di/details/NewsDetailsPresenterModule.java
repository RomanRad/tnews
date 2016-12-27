package com.rr.tnews.di.details;

import com.rr.tnews.ui.presenter.details.NewsDetailsContract;

import dagger.Module;
import dagger.Provides;

/**
 * @author Roman Radko
 * @since 1.0
 */

@Module
public class NewsDetailsPresenterModule {

    private NewsDetailsContract.View mView;

    public NewsDetailsPresenterModule(NewsDetailsContract.View view) {
        mView = view;
    }

    @Provides
    NewsDetailsContract.View provideNewsDetailsView() {
        return mView;
    }
}
