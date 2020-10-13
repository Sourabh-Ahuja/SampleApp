package com.assignmentba.ui.main;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MovieDetailFragmentProvider {

    @ContributesAndroidInjector(modules = {
            MovieDetailFragmentModule.class})
    abstract MovieDetailFragment movieDetailFragment();
}
