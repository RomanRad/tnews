package com.rr.tnews.di.details;

import com.rr.tnews.activity.DetailsActivity;
import com.rr.tnews.activity.MainActivity;
import com.rr.tnews.di.NewsComponent;
import com.rr.tnews.utils.ActivityScope;

import dagger.Component;

/**
 * @author Roman Radko
 * @since 1.0
 */
@ActivityScope
@Component(dependencies = NewsComponent.class, modules = NewsDetailsPresenterModule.class)
public interface NewsDetailsPresenterComponent {
    void inject(DetailsActivity activity);
}
