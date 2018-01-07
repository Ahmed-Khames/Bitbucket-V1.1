package com.ahmedkhames.bitbucket.GitHubAPI.Response;

import com.google.gson.annotations.SerializedName;
/**
 * Created by Ahmed on 06/01/2018.
 */

public class GitHubAPIOwnerResponse {
    @SerializedName("login")
    String gitHubLogin;
    @SerializedName("html_url")
    String ownerHMLURL;

    public String getGitHubLogin() {
        return gitHubLogin;
    }

    public void setGitHubLogin(String gitHubLogin) {
        this.gitHubLogin = gitHubLogin;
    }

    public String getOwnerHMLURL() {
        return ownerHMLURL;
    }

    public void setOwnerHMLURL(String ownerHMLURL) {
        this.ownerHMLURL = ownerHMLURL;
    }
}
