package com.nanodegree.popularmoviesjava.movies.module;


import com.nanodegree.popularmoviesjava.common.CustomScope;
import com.nanodegree.popularmoviesjava.movies.presenter.MoviePresenter;
import com.nanodegree.popularmoviesjava.movies.presenter.impl.MoviePresenterImpl;
import com.nanodegree.popularmoviesjava.movies.view.MovieView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by luan_ on 04/06/2017.
 */


@Module
public class MovieModule {

    private final MovieView mView;

    public MovieModule(MovieView mView) {
        this.mView = mView;
    }

    @Provides
    @CustomScope
    MovieView providerMovieView(){
        return mView;
    }

    @Provides
    @CustomScope
    MoviePresenter providerMoviePresenter(MoviePresenterImpl presenter){
        return presenter;
    }
}