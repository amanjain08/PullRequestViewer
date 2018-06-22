package com.juggad.pullrequests.data.repository;

import com.juggad.pullrequests.data.model.PullRequestModel;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Aman Jain on 22/06/18.
 */
public interface PullRequestService {
    String API_GITHUB_URL = "https://api.github.com/";

    @GET("repos/{owner_name}/{repo}/pulls?state=open")
    Call<List<PullRequestModel>> getOpenPullRequests(@Path("owner_name") String ownerName, @Path("repo") String repoName);
}
