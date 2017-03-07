package com.abelsuviri.twitterstream.models;

import com.google.gson.annotations.SerializedName;

/**
 * This is the data model to get basic tweet information. It contains an object corresponding
 * to the user information.
 *
 * @author Abel Suviri
 */

public class TwitterStreamModel {

    @SerializedName("text")
    private String mText;

    @SerializedName("user")
    private TwitterUserModel mUser;

    @SerializedName("created_at")
    private String mPublishDate;

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public TwitterUserModel getUser() {
        return mUser;
    }

    public void setUser(TwitterUserModel user) {
        mUser = user;
    }

    public String getPublishDate() {
        return mPublishDate;
    }

    public void setPublishDate(String publishDate) {
        mPublishDate = publishDate;
    }
}
