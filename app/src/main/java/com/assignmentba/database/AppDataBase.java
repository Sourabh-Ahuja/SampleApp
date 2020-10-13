package com.assignmentba.database;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.assignmentba.AppConstants;
import com.assignmentba.models.EntityMovie;

@Database(entities = {EntityMovie.class,}, version = AppConstants.APP_DB_VERSION)
public abstract class AppDataBase extends RoomDatabase {

    public abstract DaoMovie getDaoMovie();




}
