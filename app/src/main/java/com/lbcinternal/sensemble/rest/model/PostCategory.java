package com.lbcinternal.sensemble.rest.model;

import com.google.gson.annotations.SerializedName;

public class PostCategory {

    @SerializedName("Id")
    String mId;

    @SerializedName("Title")
    String mName;

    @SerializedName("IsChecked")
    boolean mIsChecked;

    public String getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public boolean isChecked() {
        return mIsChecked;
    }
}
