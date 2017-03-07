package com.abelsuviri.twitterstream.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abelsuviri.twitterstream.R;
import com.abelsuviri.twitterstream.adapters.holder.TweetHolder;
import com.abelsuviri.twitterstream.models.TwitterStreamModel;

import java.util.List;

/**
 * This is the adapter to show a list of Tweets.
 *
 * @author Abel Suviri
 */

public class TweetAdapter extends RecyclerView.Adapter<TweetHolder> {

    private List<TwitterStreamModel> mTweets;
    private Context mContext;

    public TweetAdapter(List<TwitterStreamModel> tweets, Context context) {
        this.mTweets = tweets;
        this.mContext = context;
    }

    @Override
    public TweetHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.tweet_list,
            parent, false);

        return new TweetHolder(itemView, mContext);
    }

    @Override
    public void onBindViewHolder(TweetHolder holder, int position) {
        holder.bindTweets(mTweets.get(position));
    }

    @Override
    public int getItemCount() {
        return mTweets.size();
    }
}
