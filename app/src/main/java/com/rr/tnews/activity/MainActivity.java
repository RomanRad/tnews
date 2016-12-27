package com.rr.tnews.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.rr.tnews.NewsApplication;
import com.rr.tnews.R;
import com.rr.tnews.di.news.DaggerNewsPresenterComponent;
import com.rr.tnews.di.news.NewsPresenterModule;
import com.rr.tnews.model.News;
import com.rr.tnews.ui.adapter.NewsAdapter;
import com.rr.tnews.ui.presenter.news.NewsContract;
import com.rr.tnews.ui.presenter.news.NewsPresenter;
import com.rr.tnews.utils.ItemClickListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements NewsContract.View, ItemClickListener.OnItemClickListener {

    private SwipeRefreshLayout mNewsRefreshLayout;
    private View mEmptyView;

    private NewsAdapter mNewsAdapter;
    @Inject
    NewsPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerNewsPresenterComponent.builder().
                newsComponent(((NewsApplication) getApplication()).getNewsComponent()).
                newsPresenterModule(new NewsPresenterModule(this)).
                build().inject(this);

        mEmptyView = findViewById(R.id.empty_view);
        mNewsRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.news_list_refresh);
        mNewsRefreshLayout.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorAccent), ContextCompat.getColor(this, R.color.colorPrimary));
        mNewsRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadNews(true);
            }
        });

        mNewsAdapter = new NewsAdapter(this, new ArrayList<News>());

        RecyclerView newsList = (RecyclerView) findViewById(R.id.news_list);
        newsList.setLayoutManager(new LinearLayoutManager(this));
        newsList.setAdapter(mNewsAdapter);
        newsList.addOnItemTouchListener(new ItemClickListener(this, this));

        mPresenter.loadNews(false);
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        mNewsRefreshLayout.setRefreshing(active);
    }

    @Override
    public void showNews(List<News> news) {
        mEmptyView.setVisibility(View.GONE);
        mNewsAdapter.updateData(news);
    }

    @Override
    public void showLoadingNewsError() {
        mEmptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showNoNews() {
        mEmptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onItemClick(int position) {
        News news = mNewsAdapter.getItem(position);
        DetailsActivity.start(this, news);
    }
}
