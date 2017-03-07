package com.abelsuviri.twitterstream;

import android.app.Application;

import com.abelsuviri.twitterstream.components.ApplicationComponent;
import com.abelsuviri.twitterstream.components.DaggerApplicationComponent;
import com.abelsuviri.twitterstream.modules.ApplicationModule;
import com.abelsuviri.twitterstream.modules.NetworkModule;

/**
 * @author Abel Suviri
 */

public class TwitterStreamApp extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(new ApplicationModule(this))
            .networkModule(new NetworkModule())
            .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}
