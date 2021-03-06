package com.nanodegree.popularmoviesjava.service.request;

import com.nanodegree.popularmoviesjava.dto.GenericDTO;
import com.nanodegree.popularmoviesjava.dto.MovieDTO;
import com.nanodegree.popularmoviesjava.dto.MovieDetailDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


/**
 * Created by luanfernandes on 31/08/17.
 */
public interface PopularMoviesRequest{




    @GET("movie/{sort}")
    Call<GenericDTO<MovieDTO>> getMovies(@Path("sort") String sort, @Query("page") Integer page);

    @GET("movie/{movie_id}")
    Call<MovieDetailDTO> detailMovie(@Path("movie_id") Long movieId);

}