package com.assignmentba.ui.main;

import android.util.Log;

import com.assignmentba.datamanager.AppDataManager;
import com.assignmentba.models.EntityMovie;
import com.assignmentba.ui.main.base.BaseViewModel;
import com.assignmentba.utils.SchedulerProvider;
import com.assignmentba.utils.SingleLiveEvent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class MainViewModel extends BaseViewModel {

    private static final String TAG = "MainViewModel";


    public SingleLiveEvent<List<EntityMovie>> moviesListLiveEvent = new SingleLiveEvent<>();

    public MainViewModel(AppDataManager appDataManager, SchedulerProvider schedulerProvider) {
        super(appDataManager, schedulerProvider);
    }

    public void fetchMovieList() {

        Disposable disposable = getAppDataManager().fetchMovieList("popular", 1)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(moviesListResponseResponse -> {

                    moviesListLiveEvent.setValue(moviesListResponseResponse.body().getMovieList());

                    saveDataToDB(moviesListResponseResponse.body().getMovieList());

                }, throwable -> {

                    //showToastMessage(throwable.getMessage());

                    fetchDataFromDb();
                });

        getCompositeDisposable().add(disposable);
    }


    public SingleLiveEvent<List<EntityMovie>> getMovieList() {
        return moviesListLiveEvent;
    }

    private void saveDataToDB(List<EntityMovie> movieList) {
        Disposable disposable = getAppDataManager().insertMovieList(movieList)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(successValue -> Log.d("DataBase : ", "saving success"),
                        throwable -> Log.d("DataBase : ", throwable.getMessage()));

        getCompositeDisposable().add(disposable);
    }

    private void fetchDataFromDb() {

        Disposable disposable = getAppDataManager().getAllMovie()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(movieList -> moviesListLiveEvent.setValue(movieList), throwable -> {
                    Log.d("DataBase : ", throwable.getMessage());
                });
        getCompositeDisposable().add(disposable);

    }

}
