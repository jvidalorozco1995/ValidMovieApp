package com.example.jorge.validmovieapp;

import com.example.jorge.validmovieapp.Api.NetworkModule;
import com.example.jorge.validmovieapp.Ui.Activities.MainActivity;
import com.example.jorge.validmovieapp.Ui.Fragments.grid.MoviesGridFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface NetworkComponent {

    void inject(MoviesGridFragment moviesGridFragment);

    void inject(MainActivity mainActivity);


}
