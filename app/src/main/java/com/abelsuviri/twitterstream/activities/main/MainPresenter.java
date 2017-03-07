package com.abelsuviri.twitterstream.activities.main;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.abelsuviri.twitterstream.adapters.TweetAdapter;
import com.abelsuviri.twitterstream.domains.StreamRequest;
import com.abelsuviri.twitterstream.models.TwitterStreamModel;
import com.abelsuviri.twitterstream.utils.Constants;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import okio.BufferedSource;
import retrofit2.Retrofit;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * This is the class responsible of all data processing giving to {@link MainActivity} the only
 * responsibility of interact with the user, in this case just print information.
 *
 * @author Abel Suviri
 */

public class MainPresenter {

    private MainView mMainView;
    private Retrofit mRetrofit;
    private Context mContext;
    private TwitterStreamModel mTwitterStream;
    private List<TwitterStreamModel> mTwitterStreams = new ArrayList<>();

    @Inject
    public MainPresenter(Retrofit retrofit, Application application) {
        this.mRetrofit = retrofit;
        this.mContext = application;
    }

    /**
     * This class received the interface reference from {@link MainActivity}
     *
     * @param mainView Interface reference
     */
    public void providesMainView(MainView mainView) {
        this.mMainView = mainView;
    }

    /**
     * This method make the request to the server and retrieves tweets one by one, having
     * a connection always open.
     */
    public void getTweets() {
        mRetrofit.create(StreamRequest.class).getStream()
            .flatMap(responseBody -> events(responseBody.source()))
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .retryWhen(errors -> errors.flatMap(error -> Observable.timer(1, TimeUnit.SECONDS)))
            .subscribe((result) -> {
                mTwitterStream = new Gson().fromJson(result, TwitterStreamModel.class);
                createAdapter(mTwitterStream);
            }, (error) -> mMainView.onError(error.getMessage()));
    }

    /**
     * This method creates an observable who is subscribed always to the API waiting for a response.
     *
     * @param source Response from API
     * @return  Response as observable
     */
    @NonNull
    private static Observable<String> events(BufferedSource source) {
        return Observable.create(subscriber -> {
            try {
                while (!source.exhausted()) {
                    subscriber.onNext(source.readUtf8Line());
                }
            } catch (Exception e) {
                subscriber.onError(e);
            }
        });
    }

    /**
     * This method creates {@link TweetAdapter} and send it to {@link MainActivity}
     *
     * @param twitterStream Data model containing a single tweet.
     */
    private void createAdapter(TwitterStreamModel twitterStream) {
        /*
        If tweet is empty does nothing (sometimes a tweet comes empty because it was not
        processed as an error while retrieving from server).
         */
        if (!TextUtils.isEmpty(twitterStream.getText())) {
            /*
            If tweet limit was not reached (10 tweets) the new tweet is added at the first position
            of the list otherwise the last tweet is removed and the new one is added.
             */
            if (mTwitterStreams.size() < Constants.LIST_LIMIT) {
                mTwitterStreams.add(0, twitterStream);
            } else {
                mTwitterStreams.remove(mTwitterStreams.size() - 1);
                mTwitterStreams.add(0, twitterStream);
            }

            mMainView.onRequestFinished(new TweetAdapter(mTwitterStreams, mContext));
        }
    }
}
