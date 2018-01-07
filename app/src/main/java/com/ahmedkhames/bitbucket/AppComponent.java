package com.ahmedkhames.bitbucket;

import javax.inject.Singleton;

import dagger.Component;
import  com.ahmedkhames.bitbucket.GitHubAPI.GitHubAPIModule;
import  com.ahmedkhames.bitbucket.GitHubRepoList.GitHubRepoListComponent;
import  com.ahmedkhames.bitbucket.GitHubRepoList.GitHubRepoListModule;
/**
 * Created by Ahmed on 07/01/2018.
 */

@Singleton
@Component(modules = {AppModule.class, GitHubAPIModule.class})
public interface AppComponent {
    //GitHubRepoListComponent will have access to instances produced by AppModule and GitHubAPIModule
    // as it is a subcomponent of AppComponent
    GitHubRepoListComponent inject(GitHubRepoListModule module);
}
