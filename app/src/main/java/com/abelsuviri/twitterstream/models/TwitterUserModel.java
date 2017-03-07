package com.abelsuviri.twitterstream.models;

import com.google.gson.annotations.SerializedName;

/**
 * This is the data model to get the user information.
 *
 * @author Abel Suviri
 */

public class TwitterUserModel {

    @SerializedName("name")
    private String mName;

    @SerializedName("screen_name")
    private String mScreenName;

    @SerializedName("profile_image_url")
    private String mProfilePicture;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getScreenName() {
        return mScreenName;
    }

    public void setScreenName(String screenName) {
        mScreenName = screenName;
    }

    public String getProfilePicture() {
        return mProfilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        mProfilePicture = profilePicture;
    }
}
