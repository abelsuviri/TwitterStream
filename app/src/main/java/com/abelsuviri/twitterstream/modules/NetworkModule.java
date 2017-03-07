package com.abelsuviri.twitterstream.modules;

import com.abelsuviri.twitterstream.BuildConfig;
import com.abelsuviri.twitterstream.utils.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer;
import se.akerfeldt.okhttp.signpost.SigningInterceptor;

/**
 * This module provides all the networking tools to make request to Twitter API.
 *
 * @author Abel Suviri
 */

@Module
public class NetworkModule {

    public NetworkModule() {

    }

    @Provides
    @Singleton
    OkHttpClient providesOkHttpClient() {
        OkHttpOAuthConsumer consumer = new OkHttpOAuthConsumer(BuildConfig.CONSUMER_KEY,
            BuildConfig.CONSUMER_SECRET);
        consumer.setTokenWithSecret(BuildConfig.ACCESS_TOKEN, BuildConfig.ACCESS_SECRET);

        return new OkHttpClient.Builder()
            .addInterceptor(new SigningInterceptor(consumer))
            .build();
    }

    @Provides
    @Singleton
    Retrofit providesRetrofit(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .build();

        return retrofit;
    }
}
