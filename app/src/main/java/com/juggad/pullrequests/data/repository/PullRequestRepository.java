package com.juggad.pullrequests.data.repository;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Aman Jain on 22/06/18.
 */
public class PullRequestRepository {

    public PullRequestService mPullRequestService;
    public Retrofit retrofit;

    private static PullRequestRepository mPullRequestRepository;

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private PullRequestRepository() {
        retrofit = new Retrofit.Builder()
                .baseUrl(PullRequestService.API_GITHUB_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mPullRequestService = retrofit.create(PullRequestService.class);
    }

    public synchronized static PullRequestRepository getInstance() {
        if (mPullRequestRepository == null) {
            mPullRequestRepository = new PullRequestRepository();
        }
        return mPullRequestRepository;
    }
}
