package com.assignmentba.ui.main;

import androidx.fragment.app.Fragment;
import com.assignmentba.R;
import com.assignmentba.databinding.ActivityMainBinding;
import com.assignmentba.models.EntityMovie;
import com.assignmentba.ui.main.base.BaseActivity;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> implements HasSupportFragmentInjector,
        FragmentCommunicationListener {

    private static final String TAG = "MainActivity";
    public static final String HOME_FRAGMENT = "homeFragment";
    public static final String MOVIE_DETAIL_FRAGMENT = "movieDetailFragment";

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Inject
    MainViewModel mainActivityViewModel;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected MainViewModel getViewModel() {
        return mainActivityViewModel;
    }

    @Override
    protected void initObservers() {

    }

    @Override
    protected void setUp() {
        loadHomeFragment();
    }


    private void loadHomeFragment() {
        HomeFragment homeFragment = HomeFragment.newInstance();
        homeFragment.setFragmentCommunicationListener(this);

        loadFragment(homeFragment, HOME_FRAGMENT);
    }

    private void loadDetailFragment(EntityMovie movie) {
        MovieDetailFragment movieDetailFragment = MovieDetailFragment.newInstance(movie);
        movieDetailFragment.setListener(this);

        loadFragment(movieDetailFragment, MOVIE_DETAIL_FRAGMENT);
    }


    private void loadFragment(Fragment fragment, String tag) {

        /**
         *  #getSupportFragmentManager().beginTransaction  -    //create a transaction for transition here
         *  #replace() -     // put the fragment in place
         *  #addToBackStack() -     // this is the part that will cause a fragment to be added to backstack,
         *                          // this way we can return to it at any time using this tag
         *
         */


        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, fragment, tag)
                .addToBackStack(tag)
                .commit();
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }

    /**
     * Fragment communication listener {@link FragmentCommunicationListener}
     *
     * @param movie
     */
    @Override
    public void onMovieClick(EntityMovie movie) {
        loadDetailFragment(movie);
    }

    @Override
    public void onBackButtonClick() {

        onBackPressed();
    }

    @Override
    public void onMenuButtonClick(List<EntityMovie> entityMovies) {
    }


    @Override
    public void onBackPressed() {

        if (getSupportFragmentManager() != null && getSupportFragmentManager().findFragmentByTag(MOVIE_DETAIL_FRAGMENT) != null) {
            // I'm viewing MovieDetailFragment
            getSupportFragmentManager().popBackStackImmediate(HOME_FRAGMENT, 0);
        } else {
            finish();
        }
    }

}