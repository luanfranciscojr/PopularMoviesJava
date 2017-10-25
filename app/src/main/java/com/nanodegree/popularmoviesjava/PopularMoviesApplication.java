package com.nanodegree.popularmoviesjava;

import android.app.Application;

import com.nanodegree.popularmoviesjava.service.component.DaggerServiceComponent;
import com.nanodegree.popularmoviesjava.service.component.ServiceComponent;
import com.nanodegree.popularmoviesjava.service.module.ServiceModule;


/**
 * Created by luan_ on 31/05/2017.
 */

public class PopularMoviesApplication extends Application {

    private ServiceComponent serviceComponent;


    public void onCreate() {
        super.onCreate();
        serviceComponent = DaggerServiceComponent.builder()
                .serviceModule(new ServiceModule())
                .build();
    }


    public ServiceComponent getServiceComponent() {
        return serviceComponent;
    }
}