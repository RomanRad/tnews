package com.rr.tnews.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.rr.tnews.NewsApplication;
import com.rr.tnews.R;
import com.rr.tnews.di.details.DaggerNewsDetailsPresenterComponent;
import com.rr.tnews.di.details.NewsDetailsPresenterModule;
import com.rr.tnews.model.News;
import com.rr.tnews.ui.presenter.details.NewsDetailsContract;
import com.rr.tnews.ui.presenter.details.NewsDetailsPresenter;

import javax.inject.Inject;

/**
 * @author Roman
 * @since 1.0
 */

public class DetailsActivity extends AppCompatActivity implements NewsDetailsContract.View {

    private static final String ID_EXTRA = "news_id:extra";
    private static final String NAME_EXTRA = "news_name:extra";

    private TextView mDetails;
    private View mEmptyView;
    private View mLoadingView;

    @Inject
    NewsDetailsPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(Html.fromHtml(getIntent().getStringExtra(NAME_EXTRA)));

        DaggerNewsDetailsPresenterComponent.builder().
                newsComponent(((NewsApplication) getApplication()).getNewsComponent()).
                newsDetailsPresenterModule(new NewsDetailsPresenterModule(this)).
                build().inject(this);

        mDetails = (TextView) findViewById(R.id.news_details);
        mDetails.setMovementMethod(LinkMovementMethod.getInstance());
        mEmptyView = findViewById(R.id.empty_view);
        mLoadingView = findViewById(R.id.loading_progress);

        long id = getIntent().getLongExtra(ID_EXTRA, -1);
        mPresenter.loadNewsContent(id);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        mLoadingView.setVisibility(active ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showNews(News news) {
        mDetails.setText(Html.fromHtml(news.getContent()));
    }

    @Override
    public void showLoadingNewsError() {
        mEmptyView.setVisibility(View.VISIBLE);
    }

    public static void start(Context context, News news) {
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra(ID_EXTRA, news.getId());
        intent.putExtra(NAME_EXTRA, news.getText());
        context.startActivity(intent);
    }
}
