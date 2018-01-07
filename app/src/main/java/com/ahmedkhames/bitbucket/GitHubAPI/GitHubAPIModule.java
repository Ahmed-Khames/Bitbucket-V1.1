package com.ahmedkhames.bitbucket.GitHubAPI;

import android.app.Application;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import com.ahmedkhames.bitbucket.R;
/**
 * Created by Ahmed on 07/01/2018.
 */

@Module
public class GitHubAPIModule {

    private Application application;

    public GitHubAPIModule(Application application) {
        this.application = application;
    }

    public GitHubAPIModule(){}

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient() {
        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(30, TimeUnit.SECONDS);
        client.setReadTimeout(30, TimeUnit.SECONDS);
        return client;
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(Application application, OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(application.getString(R.string.github_base_url))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    public GitHubAPIServices provideApiService(Retrofit retrofit) {
        return retrofit.create(GitHubAPIServices.class);
    }
}
