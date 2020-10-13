package com.assignmentba.network;


import com.assignmentba.models.MoviesListResponse;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApiService {

    @GET("movie/{type}?language=en-US&region=US")
    Single<Response<MoviesListResponse>> fetchMoviesByType(@Path("type") String type, @Query("page") long page);

}

