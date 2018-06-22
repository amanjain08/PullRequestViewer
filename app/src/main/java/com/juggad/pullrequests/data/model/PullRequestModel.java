package com.juggad.pullrequests.data.model;

import android.arch.lifecycle.LiveData;

/**
 * Created by Aman Jain on 22/06/18.
 */
public class PullRequestModel extends LiveData {

    String url;
    String title;
    String body;
    String createdAt;
    String updatedAt;
    User user;

    public PullRequestModel(final String url, final String title, final String body, final String createdAt,
            final String updatedAt,
            final User user) {
        this.url = url;
        this.title = title;
        this.body = body;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.user = user;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(final String body) {
        this.body = body;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(final String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(final String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }
}
