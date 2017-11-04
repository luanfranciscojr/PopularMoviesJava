package com.nanodegree.popularmoviesjava.movies.repository.callback;

import com.nanodegree.popularmoviesjava.dto.GenericDTO;
import com.nanodegree.popularmoviesjava.dto.MovieDTO;
import com.nanodegree.popularmoviesjava.service.request.PopularMoviesRequest;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by luan_ on 04/11/2017.
 */

public class MovieRepository {

    private Retrofit retrofit;

    @Inject
    public MovieRepository(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public void loadMovies(String sort, Integer page, final Callback<GenericDTO<MovieDTO>> callback) {
        PopularMoviesRequest popularMoviesRequest = retrofit.create(PopularMoviesRequest.class);
        Call<GenericDTO<MovieDTO>> call = popularMoviesRequest.getMovies(sort, page);
        call.enqueue(new retrofit2.Callback<GenericDTO<MovieDTO>>() {
            @Override
            public void onResponse(Call<GenericDTO<MovieDTO>> call, Response<GenericDTO<MovieDTO>> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                }else{
                    callback.onError(null);
                }
            }

            @Override
            public void onFailure(Call<GenericDTO<MovieDTO>> call, Throwable t) {
                callback.onError(t);
            }
        });
    }
}
