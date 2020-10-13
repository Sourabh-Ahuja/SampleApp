package com.assignmentba.ui.main;


import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.assignmentba.R;
import com.assignmentba.databinding.ItemMovieBinding;
import com.assignmentba.models.EntityMovie;
import com.assignmentba.ui.main.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class HomeFragmentMovieListAdapter extends RecyclerView.Adapter<HomeFragmentMovieListAdapter.MovieItemViewHolder> {

    private static final String TAG = "HomeFragmentMovieListAd";

    @Inject
    Context context;

    public interface MovieClickListener {
        void onMovieClick(EntityMovie movie);
    }

    private List<EntityMovie> movieList = new ArrayList<>();
    private MovieClickListener movieClickListener;
    private BaseActivity baseActivity;

    public void setBaseActivity(BaseActivity activity) {
        baseActivity = activity;
    }


    public HomeFragmentMovieListAdapter() { }

    public void addData(List<EntityMovie> movieList) {
      //  this.movieList.clear();
        Log.e(TAG," movieList after clear" + this.movieList.size());
        Log.e(TAG," movieList para" + movieList.size());
        this.movieList.addAll(movieList);
        Log.e(TAG," movieList " + this.movieList.size());

        notifyDataSetChanged();
    }

    public void clearList(boolean shouldClear) {
        if (!movieList.isEmpty() && shouldClear)
            movieList.clear();
    }

    public void setMovieClickListener(MovieClickListener movieClickListener) {
        this.movieClickListener = movieClickListener;
    }

    @NonNull
    @Override
    public MovieItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        ItemMovieBinding movieItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()), R.layout.item_movie, viewGroup, false);
        return new MovieItemViewHolder(movieItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieItemViewHolder movieItemViewHolder, int i) {
        if(baseActivity != null){
            DisplayMetrics displayMetrics = new DisplayMetrics();
            baseActivity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int height = displayMetrics.heightPixels;
            int width = displayMetrics.widthPixels;
            Log.e(TAG,"width " + width + " width / 3 " + width / 3);
            Log.e(TAG,"height " + height + " width / 3 " + height / 3);
            movieItemViewHolder.movieItemBinding.containerItem.getLayoutParams().width = width / 3;
            movieItemViewHolder.movieItemBinding.containerItem.getLayoutParams().height = height / 4;
        }

        movieItemViewHolder.bindData(movieList.get(i));
    }

    @Override
    public int getItemCount() {
        return movieList == null ? 0 : movieList.size();
    }

    public class MovieItemViewHolder extends RecyclerView.ViewHolder {

        ItemMovieBinding movieItemBinding;

        public MovieItemViewHolder(@NonNull ItemMovieBinding movieItemBinding) {
            super(movieItemBinding.getRoot());
            this.movieItemBinding = movieItemBinding;
        }

        public void bindData(EntityMovie movie) {
            movieItemBinding.setMovie(movie);
            movieItemBinding.setMovieClickListener(movieClickListener);
        }
    }

}
