package com.juggad.pullrequests.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import com.juggad.pullrequests.data.model.PullRequestModel;
import com.juggad.pullrequests.data.repository.PullRequestRepository;
import com.juggad.pullrequests.utils.APIError;
import com.juggad.pullrequests.utils.ErrorUtils;
import com.juggad.pullrequests.utils.Resource;
import com.juggad.pullrequests.utils.Status;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Aman Jain on 22/06/18.
 */
public class PullRequestListViewModel extends ViewModel {

    private static final String TAG = PullRequestListViewModel.class.getSimpleName();

    private MutableLiveData<Resource<List<PullRequestModel>>> pullRequestListObservable = new MutableLiveData<>();

    public void fetchPullRequests(String ownerName, String repositoryName) {
        Resource<List<PullRequestModel>> resource = new Resource(Status.LOADING, null, null);
        pullRequestListObservable.setValue(resource);
        PullRequestRepository.getInstance().mPullRequestService.getOpenPullRequests(ownerName, repositoryName)
                .enqueue(
                        new Callback<List<PullRequestModel>>() {
                            @Override
                            public void onResponse(final Call<List<PullRequestModel>> call,
                                    final Response<List<PullRequestModel>> response) {
                                if (response.isSuccessful()) {
                                    if (response.body() != null) {
                                        Resource resource = new Resource(Status.SUCCESS, response.body(), null);
                                        pullRequestListObservable.setValue(resource);
                                    } else {
                                        Resource resource = new Resource(Status.SUCCESS, null, "No Open Pull Request");
                                        pullRequestListObservable.setValue(resource);
                                    }
                                }
                                else {
                                    APIError error = ErrorUtils.parseError(response);
                                    Resource resource = new Resource(Status.ERROR, null, error.message());
                                    pullRequestListObservable.setValue(resource);
                                }
                            }

                            @Override
                            public void onFailure(final Call<List<PullRequestModel>> call, final Throwable t) {
                                Resource resource = new Resource(Status.ERROR, null, t.getMessage());
                                pullRequestListObservable.setValue(resource);
                            }
                        });

    }

    public MutableLiveData<Resource<List<PullRequestModel>>> getPullRequestListObservable() {
        return pullRequestListObservable;
    }
}



