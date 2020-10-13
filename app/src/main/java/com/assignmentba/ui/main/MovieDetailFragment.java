package com.assignmentba.ui.main;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.ViewModelProviders;

import com.assignmentba.AppConstants;
import com.assignmentba.R;
import com.assignmentba.databinding.FragMovieDetailBinding;
import com.assignmentba.di.module.ViewModelProviderFactory;
import com.assignmentba.models.EntityMovie;
import com.assignmentba.ui.main.base.BaseFragment;

import javax.inject.Inject;

public class MovieDetailFragment extends BaseFragment<MainViewModel, FragMovieDetailBinding> {

    @Inject
    MainViewModel mainActivityViewModel;

    private EntityMovie movie;

    private FragmentCommunicationListener fragmentCommunicationListener;


    public static MovieDetailFragment newInstance(EntityMovie movie) {
        MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(AppConstants.BUNDLE_MOVIE, movie);
        movieDetailFragment.setArguments(bundle);
        return movieDetailFragment;
    }

    public void setListener(FragmentCommunicationListener fragmentCommunicationListener) {
        this.fragmentCommunicationListener = fragmentCommunicationListener;
    }


    @Override
    protected MainViewModel getViewModel() {
        return mainActivityViewModel;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.frag_movie_detail;
    }

    @Override
    public void initObservers() {

    }

    @Override
    public void setUp(View view) {

        if (getArguments() != null) {
            movie = getArguments().getParcelable(AppConstants.BUNDLE_MOVIE);

            dataBinding.setMovie(movie);
        }

        dataBinding.backButtonIv.setOnClickListener(v -> {

            if (fragmentCommunicationListener != null)
                fragmentCommunicationListener.onBackButtonClick();
        });

    }
}
