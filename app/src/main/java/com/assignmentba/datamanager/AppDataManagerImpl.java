package com.assignmentba.datamanager;


import com.assignmentba.database.AppDbHelper;
import com.assignmentba.models.EntityMovie;
import com.assignmentba.models.MoviesListResponse;
import com.assignmentba.network.AppApiHelper;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import retrofit2.Response;


@Singleton
public class AppDataManagerImpl implements AppDataManager {

    private AppApiHelper appApiHelper;
    private AppDbHelper appDbHelper;

    @Inject
    public AppDataManagerImpl(AppApiHelper appApiHelper, AppDbHelper appDbHelper) {
        this.appApiHelper = appApiHelper;
        this.appDbHelper = appDbHelper;
    }

    @Override
    public Single<Response<MoviesListResponse>> fetchMovieList(String type, long page) {
        return appApiHelper.fetchMovieList(type, page);
    }


    @Override
    public Single<List<Long>> insertMovieList(List<EntityMovie> movieList) {
        return appDbHelper.insertMovieList(movieList);
    }


    @Override
    public Single<List<EntityMovie>> getAllMovie() {
        return appDbHelper.getAllMovie();
    }
}
