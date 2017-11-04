package com.nanodegree.popularmoviesjava.movies.presenter;

import com.nanodegree.popularmoviesjava.common.SortType;

/**
 * Created by luan_ on 04/11/2017.
 */

public interface MoviePresenter {

    void loadMovies(@SortType String sortType);

    void loadNextPage();

    void resetCurrentPage();

}
