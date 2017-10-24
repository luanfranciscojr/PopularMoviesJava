package com.nanodegree.popularmoviesjava.service.component;

import com.nanodegree.popularmoviesjava.service.module.ServiceModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;


/**
 * Created by luanfernandes on 31/08/17.
 */
@Singleton
@Component(modules = {ServiceModule.class})
interface ServiceComponent {
     Retrofit retrofit();
}