package com.lbcinternal.sensemble.rest.model;

import com.google.gson.annotations.SerializedName;

public class IdeaCategory {

    @SerializedName("IsChecked")
    boolean mIsChecked;

    @SerializedName("Id")
    String mId;

    @SerializedName("Title")
    String mName;

    public boolean isChecked() {
        return mIsChecked;
    }

    public String getId() {
        return mId;
    }

    public String getTitle() {
        return mName;
    }
}
