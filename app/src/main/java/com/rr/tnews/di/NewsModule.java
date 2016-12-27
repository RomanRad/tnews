package com.rr.tnews.di;

import android.content.Context;

import com.rr.tnews.data.NewsDataSource;
import com.rr.tnews.data.NewsRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Roman
 * @since 1.0
 */

@Module
public class NewsModule {

    private Context mContext;

    public NewsModule(Context context) {
        mContext = context;
    }

    @Provides
    @Singleton
    NewsDataSource provideDataSource() {
        return new NewsRepository(mContext);
    }

}
