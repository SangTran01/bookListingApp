package com.example.tony_.booklisting;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

public class BookLoader extends AsyncTaskLoader<List<Book>> {
    private String mUrl;

    public BookLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        Log.v("MainActivity","ON START LOAD");
        forceLoad();
    }

    @Override
    public List<Book> loadInBackground() {
        List<Book> books = QueryUtils.fetchBookData(mUrl);
        return books;
    }
}
