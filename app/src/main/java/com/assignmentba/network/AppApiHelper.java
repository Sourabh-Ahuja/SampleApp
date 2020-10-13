package com.assignmentba.network;

import com.assignmentba.models.MoviesListResponse;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface AppApiHelper {

    Single<Response<MoviesListResponse>> fetchMovieList(@Path("type") String type, @Query("page") long page);

}
