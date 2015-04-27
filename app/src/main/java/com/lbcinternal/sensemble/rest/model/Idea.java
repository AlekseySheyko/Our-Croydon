package com.lbcinternal.sensemble.rest.model;

import com.google.gson.annotations.SerializedName;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

    public String getCreationDate() {
        DateFormat format = new SimpleDateFormat("d MMM");
        return format.format(mCreationDate);
    }

    public String getId() {
        return mId;
    }
}
