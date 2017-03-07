package com.abelsuviri.twitterstream.activities.main;

import com.abelsuviri.twitterstream.adapters.TweetAdapter;

/**
 * Interface to communicate {@link MainPresenter} and {@link MainActivity}
 *
 * @author Abel Suviri
 */

public interface MainView {

    void onRequestFinished(TweetAdapter adapter);

    void onError(String error);
}
