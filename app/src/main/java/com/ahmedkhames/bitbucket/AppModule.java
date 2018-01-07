package com.ahmedkhames.bitbucket;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import  com.ahmedkhames.bitbucket.Utils.Utility;

/**
 * Created by Ahmed on 06/01/2018.
 */
@Module
public class AppModule {
    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    public Utility provideUtility(){
        return new Utility();
    }
}
