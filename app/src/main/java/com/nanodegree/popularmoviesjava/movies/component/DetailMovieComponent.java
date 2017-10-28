package com.nanodegree.popularmoviesjava.movies.component;


import com.nanodegree.popularmoviesjava.common.CustomScope;
import com.nanodegree.popularmoviesjava.movies.module.DetailMovieModule;
import com.nanodegree.popularmoviesjava.service.component.ServiceComponent;

import dagger.Component;

/**
 * Created by luanfernandes on 31/08/17.
 */
@CustomScope
@Component(dependencies = {ServiceComponent.class}, modules = {DetailMovieModule.class})
public interface DetailMovieComponent {
    //void inject(DetailMovieActivity detailMovieActivity);
}