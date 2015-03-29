package com.lbcinternal.sensemble.rest.model;

import com.google.gson.annotations.SerializedName;

public class IdeaDetails {

    @SerializedName("Title")
    String mTitle;

    @SerializedName("Content")
    String mBody;

    @SerializedName("Rating")
    int mRating;

    public IdeaDetails(String title, String body, int rating) {
        mTitle = title;
        mBody = body;
        mRating = rating;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getBody() {
        return mBody;
    }

    public int getRating() {
        return mRating;
    }
}
