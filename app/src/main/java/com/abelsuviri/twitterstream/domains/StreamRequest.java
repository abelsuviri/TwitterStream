package com.abelsuviri.twitterstream.domains;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import rx.Observable;

/**
 * This is the retrofit interface where we define the endpoint we are going to call.
 *
 * @author Abel Suviri
 */

public interface StreamRequest {

    @GET("statuses/filter.json?track=banking")
    @Streaming
    Observable<ResponseBody> getStream();
}
