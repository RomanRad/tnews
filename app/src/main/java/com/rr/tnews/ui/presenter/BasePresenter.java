package com.rr.tnews.ui.presenter;

import java.lang.ref.WeakReference;

/**
 * @author Roman Radko
 * @since 1.0
 */

public class BasePresenter<T> {

    private WeakReference<T> mViewReference;

    public BasePresenter(T view) {
        mViewReference = new WeakReference<>(view);
    }

    protected T getView() {
        return mViewReference.get();
    }

    protected boolean checkView() {
        return mViewReference != null && mViewReference.get() != null;
    }
}
