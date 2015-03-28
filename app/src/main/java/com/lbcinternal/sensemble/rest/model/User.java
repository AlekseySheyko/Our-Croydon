package com.lbcinternal.sensemble.rest.model;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("success")
    private boolean mSuccess;

    @SerializedName("sessionId")
    private String mSessionId;

    @SerializedName("userDisplayName")
    private String mUsername;

    @SerializedName("userEmail")
    private String mEmail;

    @SerializedName("userMobile")
    private String mPhone;

    @SerializedName("avatar")
    private String mAvatarUrl;

    public User(boolean success, String sessionId, String username, String email, String phone, String avatarUrl) {
        mSuccess = success;
        mSessionId = sessionId;
        mUsername = username;
        mEmail = email;
        mPhone = phone;
        mAvatarUrl = avatarUrl;
    }

    public boolean isSuccess() {
        return mSuccess;
    }

    public String getSessionId() {
        return mSessionId;
    }

    public String getUsername() {
        return mUsername;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getPhone() {
        return mPhone;
    }

    public String getAvatarUrl() {
        return mAvatarUrl;
    }
}
