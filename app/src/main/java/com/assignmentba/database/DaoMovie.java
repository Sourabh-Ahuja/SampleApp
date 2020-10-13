package com.assignmentba.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.assignmentba.models.EntityMovie;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface DaoMovie {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertMovieList(List<EntityMovie> moviesList);

    @Query("SELECT * FROM entityMovie")
    Single<List<EntityMovie>> getAllMovie();
}

