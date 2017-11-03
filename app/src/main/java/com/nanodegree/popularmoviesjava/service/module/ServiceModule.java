package com.nanodegree.popularmoviesjava.service.module;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by luanfernandes on 31/08/17.
 */
@Module
public class ServiceModule {


    public static final String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/";
    private final String baseUrl = "https://api.themoviedb.org/3/";
    private final String apiKey = "";

    @Provides
    @Singleton
    Gson provideGson() {
        final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                    @Override
                    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        try {
                            return df.parse(json.getAsString());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }



                }).create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkhttpClient()
    {
        final OkHttpClient.Builder client =  new OkHttpClient.Builder();
        client.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                HttpUrl url = request.url().newBuilder().addQueryParameter("api_key", apiKey).build();
                request = request.newBuilder().url(url).build();
                return  chain.proceed(request);
            }
        });
        return client.build();
    }


    @Provides
    @Singleton
    Retrofit retrofit(Gson gson, OkHttpClient client) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson)).
                        client(client)
                .baseUrl(baseUrl)
                .build();
    }

}

