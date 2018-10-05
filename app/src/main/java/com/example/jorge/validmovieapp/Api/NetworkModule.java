package com.example.jorge.validmovieapp.Api;



import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.jorge.validmovieapp.Data.FavoritesService;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import dagger.Module;
import dagger.Provides;


/*
@Author: Jorge V
Clase modulo de red, con retrofit
*/
@Module
public class NetworkModule {

    private static final String BASE_URL = "http://api.themoviedb.org/3/";
    private static final long CACHE_SIZE = 10 * 1024 * 1024;    // 10 MB
    private static final int CONNECT_TIMEOUT = 15;
    private static final int WRITE_TIMEOUT = 60;
    private static final int TIMEOUT = 60;


    @Provides
    @Singleton
    Cache providesOkHttpCache(Application application) {
        return new Cache(application.getCacheDir(), CACHE_SIZE);
    }

    @Provides
    @Singleton
    OkHttpClient providesOkHttpClient(Cache cache) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        Builder builder = new Builder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(new AuthorizationInterceptor())
                .cache(cache);

        return builder.build();
    }

    @Provides
    @Singleton
    Retrofit providesRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }


    @Provides
    @Singleton
    IMovieService providesMovieService(Retrofit retrofit) {
        return retrofit.create(IMovieService.class);
    }

    /*
    @Author: Jorge V
    Proveer servicio de peliculas
    */
    @Provides
    @Singleton
    public MoviesService providesMoviesService(Application application, IMovieService movieServiceRepository) {
        return new MoviesService(application.getApplicationContext(), movieServiceRepository);
    }

    /*
    @Author: Jorge V
    Listado de favoritos servicio
    */
    @Provides
    @Singleton
    public FavoritesService providesFavoritesService(Application application) {
        return new FavoritesService(application.getApplicationContext());
    }
}
