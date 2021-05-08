package com.gormal.gormalonetask.core.networking;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class DatabaseModule {
    @Provides
    @Singleton
    public static RemoteDatabase provideDB(Application application){
        return new RemoteDatabase(application);
    }
    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(Application application) {
        return application.getSharedPreferences("GORMAL_APP_PREFERENCES", Context.MODE_PRIVATE);
    }

    /*@Provides
    @Singleton
    AppState provideAppState(SharedPreferences sharedPreferences) {
        return new AppState(sharedPreferences);
    }*/
}