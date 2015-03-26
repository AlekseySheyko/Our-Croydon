package com.lbcinternal.sensemble.views;

import java.util.Date;

public class FeedEntry {

    String mTitle;
    String mBody;
    Date mCreationDate;

    public FeedEntry(String title, String body, Date creationDate) {
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
