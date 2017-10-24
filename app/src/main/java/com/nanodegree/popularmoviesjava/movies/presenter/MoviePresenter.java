package com.nanodegree.popularmoviesjava.movies.presenter;


import com.nanodegree.popularmoviesjava.dto.GenericDTO;
import com.nanodegree.popularmoviesjava.dto.MovieDTO;
import com.nanodegree.popularmoviesjava.movies.view.MovieView;
import com.nanodegree.popularmoviesjava.service.request.PopularMoviesRequest;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by luanfernandes on 31/08/17.
 */
public class MoviePresenter implements Callback<GenericDTO<MovieDTO>> {


    private Integer currentPage = 1;
    private boolean isTopRatedRequest;
    private boolean isLastPage;
    private boolean isLoading;
    private GenericDTO<MovieDTO> movies;
    private MovieView view;
    private Retrofit retrofit;

    @Inject
    MoviePresenter( Retrofit retrofit, MovieView view){
        this.retrofit = retrofit;
        this.view = view;
    }

    public void loadTopRatedMovies() {
        isTopRatedRequest = true;
        if (isFirstPage()) {
            view.showProgress();
        }
        isLoading = true;
        PopularMoviesRequest popularMoviesRequest  = retrofit.create(PopularMoviesRequest.class);
        Call<GenericDTO<MovieDTO>> call = popularMoviesRequest.topReatedMovies(currentPage);
        call.enqueue(this);
    }


    public void  loadPopularMovies() {
        isTopRatedRequest = false;
        if (isFirstPage()) {
            view.showProgress();
        }
        isLoading = true;
        PopularMoviesRequest popularMoviesRequest  = retrofit.create(PopularMoviesRequest.class);
        Call<GenericDTO<MovieDTO>> call = popularMoviesRequest.popularMovies(currentPage);
        call.enqueue(this);
    }





    private boolean isFirstPage() {
       return currentPage == 1;
    }

    void loadNextPage() {
        if (isLastPage || isLoading) {
            return;
        }
        currentPage++;
        if (isTopRatedRequest) {
            loadTopRatedMovies();
        } else {
            loadPopularMovies();
        }

    }

    @Override
    public void onResponse(Call<GenericDTO<MovieDTO>> call, Response<GenericDTO<MovieDTO>> response) {
        if (response.isSuccessful()) {
            movies = response.body();
            view.showResult(response.body().getResult());
            isLastPage = movies.getTotalPages().equals(currentPage);
        } else {
            currentPage = movies !=null ? movies.getPage():1;
        }
        isLoading = false;
        if (isFirstPage()) view.hideProgress();
    }

    @Override
    public void onFailure(Call<GenericDTO<MovieDTO>> call, Throwable t) {
        if (isFirstPage()) view.hideProgress();
        isLoading = false;
        currentPage = movies !=null ? movies.getPage():1;
    }
}