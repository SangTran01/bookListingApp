package com.example.tony_.booklisting;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class Book {
    private String mTitle;
    private ArrayList<String> mAuthors;
    private String mYear;
    private String mDescription;
    private String mRating;
    private Bitmap mCoverUrl;

    public Book(String mTitle, ArrayList<String> mAuthors, String mYear, String mDescription, String mRating, Bitmap mCoverUrl) {
        this.mTitle = mTitle;
        this.mAuthors = mAuthors;
        this.mYear = mYear;
        this.mDescription = mDescription;
        this.mRating = mRating;
        this.mCoverUrl = mCoverUrl;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public ArrayList<String> getmAuthors() {
        return mAuthors;
    }

    public void setmAuthors(ArrayList<String> mAuthors) {
        this.mAuthors = mAuthors;
    }

    public String getmYear() {
        return mYear;
    }

    public void setmYear(String mYear) {
        this.mYear = mYear;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmRating() {
        return mRating;
    }

    public void setmRating(String mRating) {
        this.mRating = mRating;
    }

    public Bitmap getmCoverUrl() {
        return mCoverUrl;
    }

    public void setmCoverUrl(Bitmap mCoverUrl) {
        this.mCoverUrl = mCoverUrl;
    }
}
