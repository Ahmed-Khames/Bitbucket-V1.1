package com.ahmedkhames.bitbucket.GitHubAPI;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;
import com.ahmedkhames.bitbucket.GitHubAPI.Response.GitHubAPIRepoResponse;


public interface GitHubAPIServices {
    @GET("/users/{username}/repos")
    Observable<List<GitHubAPIRepoResponse>> getRepositoryList(
            @Path("username") String username,
            @Query("page") int pageNumber,
            @Query("per_page") int repoPerPage
    );
}
