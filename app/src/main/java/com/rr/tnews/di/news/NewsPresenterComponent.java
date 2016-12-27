package com.rr.tnews.di.news;

import com.rr.tnews.activity.MainActivity;
import com.rr.tnews.di.NewsComponent;
import com.rr.tnews.utils.ActivityScope;

import dagger.Component;

/**
 * @author Roman Radko
 * @since 1.0
 */
@ActivityScope
@Component(dependencies = NewsComponent.class, modules = NewsPresenterModule.class)
public interface NewsPresenterComponent {
    void inject(MainActivity activity);
}
