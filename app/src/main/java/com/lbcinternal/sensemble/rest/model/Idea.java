package com.lbcinternal.sensemble.rest.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Idea {

    @SerializedName("Id")
    String mId;

    @SerializedName("Title")
    String mTitle;

    @SerializedName("DateCreated")
    Date mCreationDate;

    public Idea(String id, String title, Date creationDate) {
        mId = id;
        mTitle = title;
        mCreationDate = creationDate;
    }

    public String getTitle() {
        return mTitle;
    }

    public Date getCreationDate() {
        return mCreationDate;
    }

    public String getId() {
        return mId;
    }
}
