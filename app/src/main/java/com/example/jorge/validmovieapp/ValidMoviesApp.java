package com.example.jorge.validmovieapp;

import android.app.Application;

import com.example.jorge.validmovieapp.Api.NetworkModule;

public class ValidMoviesApp extends Application {

    private NetworkComponent networkComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        networkComponent = DaggerNetworkComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .build();
    }

    public NetworkComponent getNetworkComponent() {
        return networkComponent;
    }

}

