package com.juggad.pullrequests.data.model;

/**
 * Created by Aman Jain on 22/06/18.
 */
public class User {
    String login;
    String htmlurl;

    public User(final String login, final String htmlurl) {
        this.login = login;
        this.htmlurl = htmlurl;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(final String login) {
        this.login = login;
    }

    public String getHtmlurl() {
        return htmlurl;
    }

    public void setHtmlurl(final String htmlurl) {
        this.htmlurl = htmlurl;
    }
}
