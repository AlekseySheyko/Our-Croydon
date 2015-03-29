package com.lbcinternal.sensemble.rest.model;

import java.util.Date;

public class IdeaEntry {

    String mTitle;
    Date mCreationDate;

    public IdeaEntry(String title, Date creationDate) {
        mTitle = title;
        mCreationDate = creationDate;
    }

    public String getTitle() {
        return mTitle;
    }

    public Date getCreationDate() {
        return mCreationDate;
    }
}
