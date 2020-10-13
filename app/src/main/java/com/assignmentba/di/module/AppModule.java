package com.assignmentba.di.module;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.assignmentba.R;
import com.assignmentba.database.AppDataBase;
import com.assignmentba.di.ApplicationContext;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    @ApplicationContext
    Context providesContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    AppDataBase providesAppDataBase(@ApplicationContext Context context) {
        return Room.databaseBuilder(context, AppDataBase.class, "BADatabase").fallbackToDestructiveMigration().build();
    }

}
