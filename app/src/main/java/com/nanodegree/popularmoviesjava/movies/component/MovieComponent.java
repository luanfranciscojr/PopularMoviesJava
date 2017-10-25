package com.nanodegree.popularmoviesjava.movies.component;


import com.nanodegree.popularmoviesjava.common.CustomScope;
import com.nanodegree.popularmoviesjava.movies.activity.MovieActivity;
import com.nanodegree.popularmoviesjava.movies.module.MovieModule;
import com.nanodegree.popularmoviesjava.service.component.ServiceComponent;

import dagger.Component;

/**
 * Created by luanfernandes on 31/08/17.
 */
@CustomScope
@Component(dependencies ={ServiceComponent.class}, modules = {MovieModule.class})
public interface MovieComponent {
     void inject(MovieActivity movieActivity);
}