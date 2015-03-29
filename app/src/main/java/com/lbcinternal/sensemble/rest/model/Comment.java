package com.lbcinternal.sensemble.rest.model;

import com.google.gson.annotations.SerializedName;

public class Comment {

    @SerializedName("AuthorName")
    String mAuthorName;

    @SerializedName("Avatar")
    String mAvatarUrl;

    @SerializedName("Content")
    String mMessage;

    public Comment(String authorName, String avatarUrl, String message) {
        mAuthorName = authorName;
        mAvatarUrl = avatarUrl;
        mMessage = message;
    }

    public String getAuthorName() {
        return mAuthorName;
    }

    public String getAvatarUrl() {
        return "https://lbc-shapecroydon-ci-dev.azurewebsites.net"
                + mAvatarUrl;
    }

    public String getMessage() {
        return mMessage;
    }
}
