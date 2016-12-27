package com.rr.tnews;

import android.app.Application;

import com.rr.tnews.di.DaggerNewsComponent;
import com.rr.tnews.di.NewsComponent;
import com.rr.tnews.di.NewsModule;

/**
 * @author Roman
 * @since 1.0
 */

public class NewsApplication extends Application {

    private NewsComponent mNewsComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mNewsComponent = DaggerNewsComponent.builder().newsModule(new NewsModule(getApplicationContext())).build();
    }

    public NewsComponent getNewsComponent() {
        return mNewsComponent;
    }
}
