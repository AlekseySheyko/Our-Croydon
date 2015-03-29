package com.lbcinternal.sensemble.rest.model;

import com.google.gson.annotations.SerializedName;

public class IdeaDetails {

    @SerializedName("Title")
    String mTitle;

    @SerializedName("Content")
    String mBody;

    public IdeaDetails(String title, String body) {
        mTitle = title;
        mBody = body;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getBody() {
        return mBody;
    }
}
