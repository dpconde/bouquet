package com.dpconde.bouquet.di.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import dagger.Module;
import dagger.Provides;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by dpconde on 28/9/18.
 */

@Module
public class GsonModule {


    @Provides
    public Gson gson(){
        GsonBuilder gsonBuilder  = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .setLenient();

        return gsonBuilder.create();
    }


    @Provides
    public GsonConverterFactory gsonConverterFactory(Gson gson){
        return GsonConverterFactory.create(gson);
    }

}
