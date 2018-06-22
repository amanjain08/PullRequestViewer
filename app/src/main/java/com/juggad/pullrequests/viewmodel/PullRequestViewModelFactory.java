package com.juggad.pullrequests.viewmodel;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

/**
 * Created by Aman Jain on 22/06/18.
 */
public class PullRequestViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private Application mApplication;
    private String mOwnerName;
    private String mRepoName;

    public PullRequestViewModelFactory(final Application application, final String ownerName, final String repoName) {
        mApplication = application;
        mOwnerName = ownerName;
        mRepoName = repoName;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new PullRequestListViewModel(mApplication, mOwnerName,mRepoName);
    }
}
