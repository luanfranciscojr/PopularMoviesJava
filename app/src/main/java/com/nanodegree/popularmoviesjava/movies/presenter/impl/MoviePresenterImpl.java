package com.nanodegree.popularmoviesjava.movies.presenter.impl;


import com.nanodegree.popularmoviesjava.common.SortType;
import com.nanodegree.popularmoviesjava.dto.GenericDTO;
import com.nanodegree.popularmoviesjava.dto.MovieDTO;
import com.nanodegree.popularmoviesjava.movies.presenter.MoviePresenter;
import com.nanodegree.popularmoviesjava.movies.repository.callback.Callback;
import com.nanodegree.popularmoviesjava.movies.repository.callback.MovieRepository;
import com.nanodegree.popularmoviesjava.movies.view.MovieView;

import javax.inject.Inject;

/**
 * Created by luanfernandes on 31/08/17.
 */
public class MoviePresenterImpl implements MoviePresenter, Callback<GenericDTO<MovieDTO>> {


    public Integer currentPage = 1;
    private boolean isLastPage;
    private boolean isLoading;
    private GenericDTO<MovieDTO> movies;
    private MovieView view;
    @SortType
    private String sortType;

    @Inject
    MovieRepository repository;

    @Inject
    public MoviePresenterImpl(MovieView view) {
        this.view = view;
    }

    public void loadMovies(@SortType String sortType) {
        if (isFirstPage()) {
            view.showProgress();
        }
        isLoading = true;
        this.sortType = sortType;
        repository.loadMovies(sortType, currentPage, this);
    }


    private boolean isFirstPage() {
        return currentPage == 1;
    }

    public void loadNextPage() {
        if (isLastPage || isLoading) {
            return;
        }
        currentPage++;


        loadMovies(this.sortType);

    }

    @Override
    public void resetCurrentPage() {
        this.currentPage = 1;
    }


    @Override
    public void onSuccess(GenericDTO<MovieDTO> movies) {
        this.movies = movies;
        view.showResult(this.movies.getResults());
        isLastPage = this.movies.getTotalPages().equals(currentPage);
        isLoading = false;
        if (isFirstPage()) view.hideProgress();
    }

    @Override
    public void onError(Throwable throwable) {
        if (isFirstPage()) view.hideProgress();
        isLoading = false;
        currentPage = movies != null ? movies.getPage() : 1;
    }
}