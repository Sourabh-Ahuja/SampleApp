package com.assignmentba.ui.main.base;


import androidx.lifecycle.ViewModel;

import com.assignmentba.datamanager.AppDataManager;
import com.assignmentba.utils.SchedulerProvider;
import com.assignmentba.utils.ToastMessageLiveEvent;

import io.reactivex.disposables.CompositeDisposable;


public abstract class BaseViewModel extends ViewModel {

    private final ToastMessageLiveEvent toastMessageLiveEvent = new ToastMessageLiveEvent();

    public AppDataManager appDataManager;
    public SchedulerProvider schedulerProvider;
    private CompositeDisposable compositeDisposable;


    public BaseViewModel(AppDataManager appDataManager, SchedulerProvider schedulerProvider) {
        this.appDataManager = appDataManager;
        this.schedulerProvider = schedulerProvider;

        this.compositeDisposable = new CompositeDisposable();
    }


    public AppDataManager getAppDataManager() {
        return appDataManager;
    }

    public SchedulerProvider getSchedulerProvider() {
        return schedulerProvider;
    }

    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }

    public ToastMessageLiveEvent getToastMessageLiveEvent() {
        return toastMessageLiveEvent;
    }

    public void showToastMessage(String toastMessage) {
        toastMessageLiveEvent.setValue(toastMessage);
    }

    @Override
    protected void onCleared() {
        try {
            compositeDisposable.dispose();
            super.onCleared();
        } catch (Exception e) {
            //do nothing
        }
    }

}
