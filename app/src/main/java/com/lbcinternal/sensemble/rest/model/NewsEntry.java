package com.lbcinternal.sensemble.rest.model;

import java.util.Date;

public class NewsEntry {

    String mTitle;
    String mBody;
    Date mCreationDate;

    public NewsEntry(String title, String body, Date creationDate) {
        mTitle = title;
        mBody = body;
        mCreationDate = creationDate;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getBody() {
        return mBody;
    }

    public Date getCreationDate() {
        return mCreationDate;
    }
}
