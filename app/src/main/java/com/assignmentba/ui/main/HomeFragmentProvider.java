package com.assignmentba.ui.main;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class HomeFragmentProvider {

    @ContributesAndroidInjector(modules = {
            HomeFragmentModule.class,
    })
    abstract HomeFragment homeFragment();
}
