package com.ahmedkhames.bitbucket.GitHubRepoList;

import dagger.Subcomponent;
import  com.ahmedkhames.bitbucket.ActivityScope;


@ActivityScope
@Subcomponent (modules = GitHubRepoListModule.class)
public interface GitHubRepoListComponent {
    MainActivity inject(MainActivity mainActivity);
}
