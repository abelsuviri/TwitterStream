package com.abelsuviri.twitterstream.adapters.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.abelsuviri.twitterstream.R;
import com.abelsuviri.twitterstream.models.TwitterStreamModel;
import com.abelsuviri.twitterstream.models.TwitterUserModel;
import com.abelsuviri.twitterstream.utils.DateUtils;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * This class is the ViewHolder adapter for the list of tweets from the stream.
 *
 * @author Abel Suviri
 */

public class TweetHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.profilePicture)
    ImageView mProfilePicture;

    @BindView(R.id.profileName)
    TextView mProfileName;

    @BindView(R.id.profileLink)
    TextView mProfileLink;

    @BindView(R.id.publishDate)
    TextView mPublishDate;

    @BindView(R.id.tweetText)
    TextView mTweetText;

    private Context mContext;

    public TweetHolder(View itemView, Context context) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        this.mContext = context;
    }

    public void bindTweets(TwitterStreamModel twitterStream) {
        if (twitterStream != null) {
            TwitterUserModel twitterUser = twitterStream.getUser();

            mProfileName.setText(twitterUser.getName());
            mProfileLink.setText(String.format(mContext.getString(R.string.at_character),
                twitterUser.getScreenName()));
            mPublishDate.setText(DateUtils.getTweetDate(twitterStream.getPublishDate()));
            mTweetText.setText(twitterStream.getText());

            Glide
                .with(mContext)
                .load(twitterUser.getProfilePicture())
                .into(mProfilePicture);
        }
    }
}
