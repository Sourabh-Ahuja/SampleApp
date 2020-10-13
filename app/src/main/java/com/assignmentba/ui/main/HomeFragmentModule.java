package com.assignmentba.ui.main;


import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeFragmentModule {

    @Provides
    HomeFragmentMovieListAdapter provideHomeFragmentMovieListAdapter(HomeFragment homeFragment) {
        return new HomeFragmentMovieListAdapter();
    }

    @Provides
    LinearLayoutManager provideGridLayoutManager(HomeFragment homeFragment) {
        return new GridLayoutManager(homeFragment.getContext(), 2, LinearLayoutManager.VERTICAL, false);
    }
}
