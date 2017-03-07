package com.abelsuviri.twitterstream.components;

import com.abelsuviri.twitterstream.activities.main.MainActivity;
import com.abelsuviri.twitterstream.activities.main.MainPresenter;
import com.abelsuviri.twitterstream.modules.ApplicationModule;
import com.abelsuviri.twitterstream.modules.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * This is the main application component where specify we are going to inject the following
 * modules included in this component into the activity below.
 *
 * @author Abel Suviri
 */

@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface ApplicationComponent {
    void inject(MainActivity mainActivity);
}
