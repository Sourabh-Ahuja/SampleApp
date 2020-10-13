package com.assignmentba.ui.main;


import com.assignmentba.models.EntityMovie;

import java.util.ArrayList;
import java.util.List;

public interface FragmentCommunicationListener {

    void onMovieClick(EntityMovie movie);

    void onBackButtonClick();

    void onMenuButtonClick(List<EntityMovie> entityMovies);
}
