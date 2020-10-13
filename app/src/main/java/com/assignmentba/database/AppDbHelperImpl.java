package com.assignmentba.database;


import com.assignmentba.models.EntityMovie;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;


@Singleton
public class AppDbHelperImpl implements AppDbHelper {

    private final AppDataBase appDataBase;

    @Inject
    AppDbHelperImpl(AppDataBase appDataBase) {
        this.appDataBase = appDataBase;
    }

    public Single<List<Long>> insertMovieList(List<EntityMovie> movieList) {
        return Single.fromCallable(() -> appDataBase.getDaoMovie().insertMovieList(movieList));
    }

    @Override
    public Single<List<EntityMovie>> getAllMovie() {
        return appDataBase.getDaoMovie().getAllMovie();
    }
}
