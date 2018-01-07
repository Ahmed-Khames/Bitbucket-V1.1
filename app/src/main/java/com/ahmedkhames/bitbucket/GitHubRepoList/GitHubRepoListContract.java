package com.ahmedkhames.bitbucket.GitHubRepoList;

import java.util.List;

import com.ahmedkhames.bitbucket.GitHubAPI.Response.GitHubAPIRepoResponse;


public interface GitHubRepoListContract {

    public interface View{

        public void loadRepo(List<GitHubAPIRepoResponse> repoList);
        public void showPB(boolean isDisplay);
        public void showAlertDialog(GitHubAPIRepoResponse response);

    }

    public interface Action{

        public void listViewLongClick(GitHubAPIRepoResponse response);
        public void callRepoAPI(String userName, int page, int repo_perPage);
    }
}
