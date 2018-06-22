package com.juggad.pullrequests.ui;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.juggad.pullrequests.R;
import com.juggad.pullrequests.data.model.PullRequestModel;
import com.juggad.pullrequests.databinding.PullRequestItemsBinding;
import com.juggad.pullrequests.ui.PullRequestAdapter.PullRequestViewHolder;
import java.util.List;

/**
 * Created by Aman Jain on 22/06/18.
 */
public class PullRequestAdapter extends RecyclerView.Adapter<PullRequestViewHolder> {

    List<PullRequestModel> mPullRequestModels;

    public PullRequestAdapter(final List<PullRequestModel> pullRequestModels) {
        mPullRequestModels = pullRequestModels;
    }

    @NonNull
    @Override
    public PullRequestViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        PullRequestItemsBinding binding =
                DataBindingUtil.inflate(layoutInflater, R.layout.pull_request_items, parent, false);
        return new PullRequestViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final PullRequestViewHolder holder, final int position) {
        holder.mBinding.setData(mPullRequestModels.get(position));
    }

    @Override
    public int getItemCount() {
        return mPullRequestModels.size();
    }

    class PullRequestViewHolder extends RecyclerView.ViewHolder {

        private final PullRequestItemsBinding mBinding;

        public PullRequestViewHolder(final PullRequestItemsBinding pullRequestItemsBinding) {
            super(pullRequestItemsBinding.getRoot());
            this.mBinding = pullRequestItemsBinding;
        }
    }


}
