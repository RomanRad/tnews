package com.rr.tnews.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rr.tnews.R;
import com.rr.tnews.model.News;

import java.lang.ref.WeakReference;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * @author Roman
 * @since 1.0
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("MMM, dd", Locale.getDefault());

    private List<News> mData;

    private WeakReference<Context> mWeakReference;

    public NewsAdapter(Context context, List<News> data) {
        mData = data;
        mWeakReference = new WeakReference<>(context);
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewsViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.news_row, parent, false));
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        News news = mData.get(position);

        holder.mNewsName.setText(Html.fromHtml(news.getText()));
        holder.mNewsType.getDrawable().setLevel(news.getBankInfoTypeId());
        holder.mNewsDate.setText(DATE_FORMAT.format(news.getPublicationDate()));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public News getItem(int position) {
        return mData.get(position);
    }

    public void updateData(List<News> data) {
        mData = data;
        notifyDataSetChanged();
    }

    private Context getContext() {
        Context context = mWeakReference.get();
        if (context == null) {
            throw new IllegalStateException("Context is null");
        }
        return context;
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {

        TextView mNewsName;
        TextView mNewsDate;
        ImageView mNewsType;

        NewsViewHolder(View itemView) {
            super(itemView);
            mNewsName = (TextView) itemView.findViewById(R.id.news_name);
            mNewsDate = (TextView) itemView.findViewById(R.id.news_date);
            mNewsType = (ImageView) itemView.findViewById(R.id.news_info_type);
        }
    }
}
