package com.juggad.pullrequests.data.model;

/**
 * Created by Aman Jain on 22/06/18.
 */
public class RepoModel {

    String ownerName;

    String repoName;

    public RepoModel(final String ownerName, final String repoName) {
        this.ownerName = ownerName;
        this.repoName = repoName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(final String ownerName) {
        this.ownerName = ownerName;
    }

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(final String repoName) {
        this.repoName = repoName;
    }
}
