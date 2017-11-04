package com.nanodegree.popularmoviesjava.movies.view;


import com.nanodegree.popularmoviesjava.dto.MovieDTO;

import java.util.List;

/**
 * Created by luan_ on 21/08/2017.
 */

public interface MovieView {
    void showResult(List<MovieDTO> movies);

    void showProgress();

    void hideProgress();
}