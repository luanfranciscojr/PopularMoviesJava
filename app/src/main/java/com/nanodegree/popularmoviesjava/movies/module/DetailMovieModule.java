package com.nanodegree.popularmoviesjava.movies.module;


import com.nanodegree.popularmoviesjava.common.CustomScope;
import com.nanodegree.popularmoviesjava.movies.view.DetailMovieView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by luan_ on 04/06/2017.
 */


@Module
public class DetailMovieModule{

    private final DetailMovieView mView;

    public DetailMovieModule(DetailMovieView mMovieView){
        this.mView = mMovieView;
    }
    @Provides
    @CustomScope
    public DetailMovieView providerMovieDetailView(){
        return mView;
    }
}