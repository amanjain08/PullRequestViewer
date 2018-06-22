package com.juggad.pullrequests.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.juggad.pullrequests.R;
import com.juggad.pullrequests.data.model.PullRequestModel;
import com.juggad.pullrequests.ui.PullRequestAdapter.PullRequestViewHolder;
import java.util.List;

/**
 * Created by Aman Jain on 22/06/18.
 */
public class PullRequestAdapter extends RecyclerView.Adapter<PullRequestViewHolder> {

    List<PullRequestModel> mPullRequestModels;

    OnItemClickListener mOnItemClickListener;

    public PullRequestAdapter(final List<PullRequestModel> pullRequestModels,
            final OnItemClickListener onItemClickListener) {
        mPullRequestModels = pullRequestModels;
        mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public PullRequestViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pull_request_items, parent, false);
        return new PullRequestViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull final PullRequestViewHolder holder, final int position) {
        final PullRequestModel data = mPullRequestModels.get(position);
        holder.title.setText(String.format("#%s, %s", data.getNumber(), data.getTitle()));
        holder.body.setText(data.getBody());
        holder.createdAt.setText(data.getCreatedAt());
        holder.updatedAt.setText(data.getUpdatedAt());
        holder.name.setText(data.getUser().getLogin());
        holder.itemView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClicked(data.getUrl());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPullRequestModels.size();
    }

    class PullRequestViewHolder extends RecyclerView.ViewHolder {

        TextView title, body, createdAt, updatedAt, name;

        public PullRequestViewHolder(final View view) {
            super(view);
            title = view.findViewById(R.id.title);
            body = view.findViewById(R.id.body);
            createdAt = view.findViewById(R.id.created_at);
            updatedAt = view.findViewById(R.id.updated_at);
            name = view.findViewById(R.id.name);

        }
    }

    interface OnItemClickListener {

        void onItemClicked(String url);
    }


}
