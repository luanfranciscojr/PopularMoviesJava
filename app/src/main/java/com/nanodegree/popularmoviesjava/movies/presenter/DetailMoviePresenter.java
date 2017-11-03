package com.nanodegree.popularmoviesjava.movies.presenter;


import com.nanodegree.popularmoviesjava.dto.MovieDetailDTO;
import com.nanodegree.popularmoviesjava.movies.view.DetailMovieView;
import com.nanodegree.popularmoviesjava.service.request.PopularMoviesRequest;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by luan_ on 03/09/2017.
 */
public class DetailMoviePresenter implements Callback<MovieDetailDTO> {

    private  Retrofit retrofit;
    private  DetailMovieView view;

    @Inject
    public DetailMoviePresenter(Retrofit retrofit, DetailMovieView view){
        this.retrofit = retrofit;
        this.view = view;
    }
    public void loadMovieDetail(Long movieId) {
        PopularMoviesRequest popularMoviesRequest = retrofit.create(PopularMoviesRequest.class);
        Call<MovieDetailDTO> call = popularMoviesRequest.detailMovie(movieId);
        call.enqueue(this);
    }

    public void onResponse(Call<MovieDetailDTO> call,  Response<MovieDetailDTO> response) {
        if(response.isSuccessful()){
            view.showDetailMovie(response.body());
        }
    }

    public void onFailure( Call<MovieDetailDTO> call, Throwable t ){

    }





}