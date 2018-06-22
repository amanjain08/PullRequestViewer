package com.juggad.pullrequests.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import com.juggad.pullrequests.data.model.PullRequestModel;
import com.juggad.pullrequests.data.repository.PullRequestRepository;
import java.util.List;

/**
 * Created by Aman Jain on 22/06/18.
 */
public class PullRequestListViewModel extends AndroidViewModel {

    private final LiveData<List<PullRequestModel>> pullRequestListObservable;

    public PullRequestListViewModel(@NonNull final Application application, final String ownerName,
            final String repoName) {
        super(application);
        pullRequestListObservable = PullRequestRepository.getInstance().getOpenPullRequests(ownerName, repoName);
    }

    public LiveData<List<PullRequestModel>> getPullRequestListObservable() {
        return pullRequestListObservable;
    }
}



