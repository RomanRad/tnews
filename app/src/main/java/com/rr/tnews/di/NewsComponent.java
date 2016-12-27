package com.rr.tnews.di;

import com.rr.tnews.data.NewsDataSource;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Roman
 * @since 1.0
 */

@Singleton
@Component(modules = NewsModule.class)
public interface NewsComponent {

    NewsDataSource getNewsDataSource();
}

