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
    float mRating;

    public String getTitle() {
        return mTitle;
    }

    public String getBody() {
        return mBody;
    }

    public int getRating() {
        return Math.round(mRating);
    }

    public String getAuthor() {
        return mAuthor;
    }
}
