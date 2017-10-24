package com.nanodegree.popularmoviesjava.service.request;

import com.nanodegree.popularmoviesjava.dto.GenericDTO;
import com.nanodegree.popularmoviesjava.dto.MovieDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


/**
 * Created by luanfernandes on 31/08/17.
 */
interface PopularMoviesRequest{


    @GET("movie/popular")
    Call<GenericDTO<MovieDTO>> popularMovies(@Query("page") Integer page);

    @GET("movie/top_rated")
    Call<GenericDTO<MovieDTO>> topReatedMovies(@Query("page") Integer page);

    @GET("movie/{movie_id}")
    Call<GenericDTO<MovieDTO>>  detailMovie(@Path("movie_id") Long movieId);

}