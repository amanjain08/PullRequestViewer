package com.juggad.pullrequests.data.repository;

import com.juggad.pullrequests.BuildConfig;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
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
        httpClient.interceptors().add(getInterceptor());
        OkHttpClient client = httpClient.build();

        retrofit = new Retrofit.Builder().client(client)
                .baseUrl(PullRequestService.API_GITHUB_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mPullRequestService = retrofit.create(PullRequestService.class);
    }

    private Interceptor getInterceptor() {
        return new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder()
                        .header("Authorization", "token " + BuildConfig.GITHUB_TOKEN)
                        .method(original.method(), original.body());

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        };
    }

    public synchronized static PullRequestRepository getInstance() {
        if (mPullRequestRepository == null) {
            mPullRequestRepository = new PullRequestRepository();
        }
        return mPullRequestRepository;
    }
}
