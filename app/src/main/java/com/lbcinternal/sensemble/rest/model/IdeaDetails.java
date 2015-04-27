package com.lbcinternal.sensemble.rest.model;

import com.google.gson.annotations.SerializedName;

public class IdeaDetails {

    @SerializedName("AuthorName")
    String mAuthor;

    @SerializedName("Title")
    String mTitle;

    @SerializedName("Content")
    String mBody;

    @SerializedName("Rating")
    int mRating;

    public String getTitle() {
        return mTitle;
    }

    public String getBody() {
        return mBody;
    }

    public int getRating() {
        return mRating;
    }

    public String getAuthor() {
        return mAuthor;
    }
}
