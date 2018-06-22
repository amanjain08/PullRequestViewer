package com.juggad.pullrequests.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.juggad.pullrequests.R;
import com.juggad.pullrequests.data.model.PullRequestModel;
import com.juggad.pullrequests.databinding.ActivityMainBinding;
import com.juggad.pullrequests.viewmodel.PullRequestListViewModel;
import com.juggad.pullrequests.viewmodel.PullRequestViewModelFactory;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mBinding;

    private RecyclerView mRecyclerView;

    List<PullRequestModel> mPullRequestModels = new ArrayList<>();

    PullRequestAdapter pullRequestAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initRecyclerView();

        PullRequestListViewModel pullRequestListViewModel = ViewModelProviders.of(this,
                new PullRequestViewModelFactory(this.getApplication(), "hyperTrack", "hyperlog-android"))
                .get(PullRequestListViewModel.class);

        pullRequestListViewModel.getPullRequestListObservable().observe(this, new Observer<List<PullRequestModel>>() {
            @Override
            public void onChanged(@Nullable final List<PullRequestModel> pullRequestModels) {
                mPullRequestModels.clear();
                mPullRequestModels.addAll(pullRequestModels);
                pullRequestAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initRecyclerView() {
        mRecyclerView = mBinding.content.recyclerView;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        pullRequestAdapter = new PullRequestAdapter(mPullRequestModels);
        mRecyclerView.setAdapter(pullRequestAdapter);
    }
}