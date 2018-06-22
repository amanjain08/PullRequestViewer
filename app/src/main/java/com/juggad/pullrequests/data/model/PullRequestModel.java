package com.juggad.pullrequests.data.model;

import android.arch.lifecycle.LiveData;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Aman Jain on 22/06/18.
 */
public class PullRequestModel extends LiveData {

    @SerializedName("html_url")
    public String url;

    public String title;

    public String body;

    @SerializedName("created_at")
    public String createdAt;

    @SerializedName("updated_at")
    public String updatedAt;

    public User user;

    public int number;

    public PullRequestModel(final String url, final String title, final String body, final String createdAt,
            final String updatedAt,
            final User user, final int number) {
        this.url = url;
        this.title = title;
        this.body = body;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.user = user;
        this.number = number;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(final int number) {
        this.number = number;
    }
}
