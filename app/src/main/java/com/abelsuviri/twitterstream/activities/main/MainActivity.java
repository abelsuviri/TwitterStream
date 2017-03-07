package com.abelsuviri.twitterstream.activities.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.abelsuviri.twitterstream.R;
import com.abelsuviri.twitterstream.TwitterStreamApp;
import com.abelsuviri.twitterstream.adapters.TweetAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements MainView {

    @Inject
    MainPresenter mMainPresenter;

    @BindView(R.id.recycleList)
    RecyclerView mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //View and component injection
        ButterKnife.bind(this);
        ((TwitterStreamApp) getApplication()).getApplicationComponent().inject(this);

        //Provide the interface reference to be used at presenter
        mMainPresenter.providesMainView(this);

        //Request tweets to presenter
        mMainPresenter.getTweets();
    }

    /**
     * Callback to be notified when a new request to the API was processed and a new adapter for the
     * list is ready to be set.
     *
     * @param adapter This is the new adapter with the latest information to be shown.
     */
    @Override
    public void onRequestFinished(TweetAdapter adapter) {
        mList.setAdapter(adapter);

        mList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mList.getContext(),
            LinearLayoutManager.VERTICAL);
        mList.addItemDecoration(dividerItemDecoration);
    }

    /**
     * Callback to be notified when an error was thrown by the API request.
     *
     * @param error This is the error message.
     */
    @Override
    public void onError(String error) {
        Log.e("Error", error);
    }
}
