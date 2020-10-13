package com.assignmentba.ui.main;


import android.app.AlertDialog;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.assignmentba.R;
import com.assignmentba.databinding.FragHomeBinding;
import com.assignmentba.di.module.ViewModelProviderFactory;
import com.assignmentba.models.EntityMovie;
import com.assignmentba.ui.main.base.BaseFragment;
import com.assignmentba.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

public class HomeFragment extends BaseFragment<MainViewModel, FragHomeBinding>
        implements HomeFragmentMovieListAdapter.MovieClickListener {

    private static final String TAG = "HomeFragment";
    String selectedOrder = "Date";
    int checkedItem = 0;

    @Inject
    MainViewModel mainActivityViewModel;

    @Inject
    HomeFragmentMovieListAdapter movieListAdapter;

    private FragmentCommunicationListener fragmentCommunicationListener;

    private List<EntityMovie> movieList = new ArrayList<>();
    private boolean isCallFromNetwork = false;


    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    public void setFragmentCommunicationListener(FragmentCommunicationListener fragmentCommunicationListener) {
        this.fragmentCommunicationListener = fragmentCommunicationListener;
    }

    @Override
    protected MainViewModel getViewModel() {
        return mainActivityViewModel;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.frag_home;
    }

    @Override
    public void initObservers() {

        mainActivityViewModel.getMovieList().observe(this,
                entityMovies -> inItRecyclerView(entityMovies));
        Log.e(TAG,"initObservers");

    }

    @Override
    public void setUp(View view) {

        if (movieList == null || movieList.isEmpty()) {
            isCallFromNetwork = true;
            dataBinding.setShouldShowLoadingBar(true);
            mainActivityViewModel.fetchMovieList();
        } else {
            isCallFromNetwork = false;
        }
        dataBinding.sort.setOnClickListener(v -> {
            showAlertDialog(movieList);
        });
    }

    private void inItRecyclerView(List<EntityMovie> movieList) {

        this.movieList.clear();
        this.movieList.addAll(Objects.requireNonNull(movieList));

        dataBinding.movieListRv.setHasFixedSize(true);
        dataBinding.setShouldShowLoadingBar(false);
        dataBinding.movieListRv.setLayoutManager(
                new GridLayoutManager(getActivity(), 3, LinearLayoutManager.VERTICAL, false));
        dataBinding.movieListRv.setAdapter(movieListAdapter);
        movieListAdapter.clearList(!isCallFromNetwork);
        movieListAdapter.addData(movieList);
        movieListAdapter.setBaseActivity(baseActivity);
        movieListAdapter.setMovieClickListener(this);
    }

    @Override
    public void onMovieClick(EntityMovie movie) {
        if (fragmentCommunicationListener != null) {
            fragmentCommunicationListener.onMovieClick(movie);
        }
    }

    private void showAlertDialog(List<EntityMovie> entityMovies) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(baseActivity);
        alertDialog.setTitle("Sort movies by");
        String[] items = {"Date","Rating"};
        if(selectedOrder.equalsIgnoreCase("Date")){
            checkedItem = 0;
        } else {
            checkedItem = 1;
        }
        alertDialog.setSingleChoiceItems(items, checkedItem, (dialog, which) -> {
            switch (which) {
                case 0:
                    selectedOrder = "Date";
                    break;
                case 1:
                    selectedOrder = "Rating";
                    break;
            }
        });

        alertDialog.setPositiveButton("Ok", (dialog, which) -> {
            Log.e(TAG,"entityMovies " + entityMovies);
            Log.e(TAG,"selectedOrder " + selectedOrder);
            if(selectedOrder.equalsIgnoreCase("Date")){
                movieListAdapter.clearList(true);
                movieListAdapter.addData(CommonUtils.getSortedByDate(entityMovies));
            } else if(selectedOrder.equalsIgnoreCase("Rating")){
                movieListAdapter.clearList(true);
                movieListAdapter.addData(CommonUtils.getSortedByVote(entityMovies));
            }
        });
        alertDialog.setNegativeButton("Cancel", (dialog, which) -> {
            dialog.dismiss();
        });
        AlertDialog alert = alertDialog.create();
        alert.setCanceledOnTouchOutside(false);
        alert.show();
    }
}
