package com.lbcinternal.sensemble.rest.model;

import java.util.Date;

public class IdeaEntry {

    int mId;
    String mTitle;
    Date mCreationDate;

    public IdeaEntry(int id, String title, Date creationDate) {
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

    public int getId() {
        return mId;
    }
}
