package com.assignmentba.di.main;

import androidx.lifecycle.ViewModelProvider;

import com.assignmentba.datamanager.AppDataManager;
import com.assignmentba.di.module.ViewModelProviderFactory;
import com.assignmentba.ui.main.MainViewModel;
import com.assignmentba.utils.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    @Provides
    MainViewModel provideMainActivityViewModel(AppDataManager appDataManager, SchedulerProvider schedulerProvider) {
        return new MainViewModel(appDataManager, schedulerProvider);
    }

    @Provides
    ViewModelProvider.Factory provideViewModelFactory(MainViewModel mainActivityViewModel) {
        return new ViewModelProviderFactory<>(mainActivityViewModel);
    }
}
