package com.rr.tnews.data.local;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.table.TableUtils;
import com.rr.tnews.data.LoadCallback;
import com.rr.tnews.data.NewsDataSource;
import com.rr.tnews.model.News;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author Roman
 * @since 1.0
 */

public class NewsLocalDataSource implements NewsDataSource {

    private Dao<News, Long> mNewsDao;

    public NewsLocalDataSource(Context context) {
        try {
            OrmLiteHelper ormLiteHelper = OpenHelperManager.getHelper(context, OrmLiteHelper.class);
            mNewsDao = ormLiteHelper.getDao(News.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getNews(LoadCallback<List<News>> callback) {
        try {
            List<News> data = mNewsDao.queryBuilder().orderBy(News.PUBLICATION_DATE_COLUMN, false).query();
            callback.onDataLoaded(data);
        } catch (SQLException e) {
            e.printStackTrace();
            callback.onLoadingFailed();
        }
    }

    @Override
    public void getNewsContent(Long newsId, LoadCallback<News> callback) {
        try {
            News news = mNewsDao.queryBuilder().where().idEq(newsId).queryForFirst();
            callback.onDataLoaded(news);
        } catch (SQLException e) {
            e.printStackTrace();
            callback.onLoadingFailed();
        }
    }

    @Override
    public void saveNews(final List<News> news) {
        try {
            mNewsDao.callBatchTasks(new Callable<News>() {
                @Override
                public News call() throws Exception {
                    for (News newElement : news) {
                        mNewsDao.create(newElement);
                    }
                    return null;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateNews(News data) {
        try {
            mNewsDao.update(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropData() {
        try {
            TableUtils.clearTable(mNewsDao.getConnectionSource(), News.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
