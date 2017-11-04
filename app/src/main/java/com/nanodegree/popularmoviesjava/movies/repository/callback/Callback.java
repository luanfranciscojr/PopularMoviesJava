package com.nanodegree.popularmoviesjava.movies.repository.callback;

/**
 * Created by luan_ on 04/11/2017.
 */

public interface Callback<T> {
    void onSuccess(T result);

    void onError(Throwable throwable);
}
