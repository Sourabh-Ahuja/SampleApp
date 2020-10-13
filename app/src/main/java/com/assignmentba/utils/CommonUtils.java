package com.assignmentba.utils;

import android.util.Log;

import com.assignmentba.models.EntityMovie;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class CommonUtils {

    private static final String TAG = "CommonUtils";

    public static List<EntityMovie> getSortedByDate(List<EntityMovie> entityMovies) {
        Collections.sort(entityMovies, (arg0, arg1) -> {
            SimpleDateFormat format = new SimpleDateFormat(
                    "yyyy-MM-dd");
            int compareResult = 0;
            try {
                Date arg0Date = format.parse(arg0.getReleaseDate());
                Date arg1Date = format.parse(arg1.getReleaseDate());
                if(arg0Date != null && arg1Date != null){
                    compareResult = arg0Date.compareTo(arg1Date);
                }
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }
            return compareResult;
        });
        Log.e(TAG,"sorted by date " + entityMovies.size());
        return entityMovies;
    }

    public static List<EntityMovie> getSortedByVote(List<EntityMovie> entityMovies) {
        Collections.sort(entityMovies,
                (entityMovie1, entityMovie2) ->
                        (int) (entityMovie1.getVoteCount()
                                - entityMovie2.getVoteCount()));
        Log.e(TAG,"sorted by popularity " + entityMovies.size());
        return entityMovies;
    }
}
