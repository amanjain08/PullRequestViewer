package com.juggad.pullrequests.ui;

import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;
import com.juggad.pullrequests.R;
import com.juggad.pullrequests.data.model.PullRequestModel;
import com.juggad.pullrequests.ui.PullRequestAdapter.OnItemClickListener;
import com.juggad.pullrequests.utils.Resource;
import com.juggad.pullrequests.utils.Status;
import com.juggad.pullrequests.viewmodel.PullRequestListViewModel;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    List<PullRequestModel> mPullRequestModels = new ArrayList<>();

    PullRequestAdapter pullRequestAdapter;

    PullRequestListViewModel pullRequestListViewModel;

    EditText mRepoNameEdit, mOwnerNameEdit;

    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        pullRequestListViewModel = ViewModelProviders.of(this)
                .get(PullRequestListViewModel.class);

        observeViewModel(pullRequestListViewModel);
    }

    /*
    Observe changes on live data
     */
    private void observeViewModel(PullRequestListViewModel pullRequestListViewModel) {
        pullRequestListViewModel.getPullRequestListObservable().observe(this,
                new Observer<Resource<List<PullRequestModel>>>() {
                    @Override
                    public void onChanged(@Nullable final Resource<List<PullRequestModel>> listResource) {
                        Status status = listResource.getStatus();
                        if (Status.LOADING == status) {
                            if (mProgressDialog != null) {
                                mProgressDialog.setMessage("Fetching...");
                                mProgressDialog.show();
                            }
                        } else if (Status.SUCCESS == status) {
                            if (mProgressDialog != null) {
                                mProgressDialog.cancel();
                            }
                            mPullRequestModels.clear();
                            if (listResource.getData() == null) {
                                showToast(listResource.getMsg());
                            } else {
                                mPullRequestModels.addAll(listResource.getData());
                            }
                            pullRequestAdapter.notifyDataSetChanged();

                        } else if (Status.ERROR == status) {
                            if (mProgressDialog != null) {
                                mProgressDialog.cancel();
                            }
                            showToast(listResource.getMsg());
                        }


                    }
                });
    }

    /*
    View initialization
     */
    private void initView() {
        mProgressDialog = new ProgressDialog(this);
        mRepoNameEdit = findViewById(R.id.repository_name);
        mOwnerNameEdit = findViewById(R.id.owner_name);
        mRecyclerView = findViewById(R.id.recycler_view);
        initializeRecyclerView();
    }

    /*
    Initialize Recycler View
     */
    private void initializeRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        pullRequestAdapter = new PullRequestAdapter(mPullRequestModels, new OnItemClickListener() {
            @Override
            public void onItemClicked(String url) {
                //Open Pull Request in browser when clicked on any pull request from list
                if (!url.startsWith("http://") && !url.startsWith("https://")) {
                    url = "http://" + url;
                }
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
            }
        });
        mRecyclerView.setAdapter(pullRequestAdapter);
    }

    /*
    Method fetch data for open pull requests and hide keyboard.
     */
    public void onSubmit(View v) {
        hideKeyboard(v);
        String repoName, ownerName;
        repoName = mRepoNameEdit.getText().toString();
        ownerName = mOwnerNameEdit.getText().toString();
        if (TextUtils.isEmpty(repoName)) {
            showToast("Please Enter Repository Name");
            return;
        }
        if (TextUtils.isEmpty(ownerName)) {
            showToast("Please Enter Owner Name");
            return;
        }
        //Fetch open pull request for this particular owner/repo
        pullRequestListViewModel.fetchPullRequests(ownerName, repoName);
    }

    /*
    Utils method to hide keyboard
     */
    public void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /*
    Utils method to show toast message
     */
    private void showToast(final String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}