package com.assignmentba.di.builder;

import com.assignmentba.di.main.MainActivityModule;
import com.assignmentba.ui.main.HomeFragmentProvider;
import com.assignmentba.ui.main.MainActivity;
import com.assignmentba.ui.main.MovieDetailFragmentProvider;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(
            modules = {
                    MainActivityModule.class,
                    HomeFragmentProvider.class,
                    MovieDetailFragmentProvider.class
            }
    )
    abstract MainActivity mainActivity();
}
