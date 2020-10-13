package com.assignmentba.database;


import com.assignmentba.models.EntityMovie;

import java.util.List;

import io.reactivex.Single;


public interface AppDbHelper {

    Single<List<Long>> insertMovieList(List<EntityMovie> movieList);

    Single<List<EntityMovie>> getAllMovie();

}
