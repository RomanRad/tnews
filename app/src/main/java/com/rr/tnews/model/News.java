package com.rr.tnews.model;

import android.support.annotation.NonNull;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * @author Roman
 * @since 1.0
 */

@DatabaseTable(tableName = "news")
public class News implements Comparable<News> {

    public static final String PUBLICATION_DATE_COLUMN = "publication_date";
    public static final String CONTENT_COLUMN = "content";

    @DatabaseField(id = true, columnName = "id")
    private long mId;

    @DatabaseField(columnName = "text")
    private String mText;

    @DatabaseField(columnName = PUBLICATION_DATE_COLUMN)
    private Date mPublicationDate;

    @DatabaseField(columnName = "info_type")
    private int mBankInfoTypeId;

    @DatabaseField(columnName = CONTENT_COLUMN)
    private String mContent;

    public News() {
    }

    public News(long id, String text, Date publicationDate, int bankInfoTypeId) {
        this(id, text, publicationDate, bankInfoTypeId, null);
    }

    public News(long id, String text, Date publicationDate, int bankInfoTypeId, String content) {
        mId = id;
        mText = text;
        mPublicationDate = publicationDate;
        mBankInfoTypeId = bankInfoTypeId;
        mContent = content;
    }

    public long getId() {
        return mId;
    }

    public String getText() {
        return mText;
    }

    public Date getPublicationDate() {
        return mPublicationDate;
    }

    public int getBankInfoTypeId() {
        return mBankInfoTypeId;
    }

    public String getContent() {
        return mContent;
    }

    @Override
    public int compareTo(@NonNull News news) {
        return news.mPublicationDate.compareTo(mPublicationDate);
    }
}
