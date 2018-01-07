package com.ahmedkhames.bitbucket.GitHubRepoList;

import android.app.Application;

import dagger.Module;
import dagger.Provides;
import  com.ahmedkhames.bitbucket.ActivityScope;
import  com.ahmedkhames.bitbucket.GitHubAPI.GitHubAPIServices;
import  com.ahmedkhames.bitbucket.Utils.Utility;


@Module
public class GitHubRepoListModule {

    private MainActivity mMainActivity;

    public GitHubRepoListModule(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    @Provides
    @ActivityScope
    MainActivity provideGitHubRepoListActivity(){
        return mMainActivity;
    }

    @Provides
    @ActivityScope
    GitHubRepoListPresenter provideGitHubRepoListPresenter(Application application, Utility utility,
                                                           GitHubAPIServices apiServices,
                                                           MainActivity mainActivity){

        return new GitHubRepoListPresenter(mainActivity,apiServices, utility,application);

    }

}
