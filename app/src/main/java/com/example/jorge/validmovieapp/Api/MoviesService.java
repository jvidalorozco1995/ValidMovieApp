package com.example.jorge.validmovieapp.Api;


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.jorge.validmovieapp.Data.MoviesContract;
import com.example.jorge.validmovieapp.Models.Movie;


import javax.inject.Inject;


import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MoviesService {

    public static final String BROADCAST_UPDATE_FINISHED = "UpdateFinished";
    public static final String EXTRA_IS_SUCCESSFUL_UPDATED = "isSuccessfulUpdated";
    private static final String LOG_TAG = "MoviesService";


    private final Context context;
    private volatile boolean loading = false;

    /*
          @Author : Jorge V
          Referencia al repositorio de retrofir
         */
    private IMovieService movieServiceRepository;

    @Inject
    public MoviesService(Context context, IMovieService movieServiceRepository) {
        this.context = context.getApplicationContext();
        this.movieServiceRepository = movieServiceRepository;
    }

    public void refreshMovies(String search) {
        if (loading) {
            return;
        }
        loading = true;

         /*
          @Author : Jorge V
          Seteamos una busqueda por default
         */
        if(search.isEmpty()){
            search = "soccer";
        }
        callDiscoverMovies(search, 1);
    }

    public boolean isLoading() {
        return loading;
    }

    public void loadMoreMovies(String search) {
        if (loading) {
            return;
        }
        loading = true;

        callDiscoverMovies(search, 1);
    }

    private void callDiscoverMovies(String search, @Nullable Integer page) {


        movieServiceRepository.searchMovies(search, page)
                .subscribeOn(Schedulers.newThread())
                .doOnNext(discoverMoviesResponse -> logResponse(discoverMoviesResponse))
                .map(discoverMoviesResponse -> discoverMoviesResponse.getResults())
                .flatMap(movies -> Observable.from(movies))

                .flatMap(detail -> movieServiceRepository.getDetailMovies(detail.getId())
                        .map(object -> {
                            object.setName(detail.getName());
                            return object;
                        })
                )
                .map(movie -> saveMovie(movie))
                .map(movieUri -> MoviesContract.MovieEntry.getIdFromUri(movieUri))
                .doOnNext(movieId -> saveMovieReference(movieId))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onCompleted() {
                        loading = false;

                        sendUpdateFinishedBroadcast(true);
                    }

                    @Override
                    public void onError(Throwable e) {
                        loading = false;
                        sendUpdateFinishedBroadcast(false);
                        Log.e(LOG_TAG, "Error", e);
                    }

                    @Override
                    public void onNext(Long aLong) {

                    }
                });
    }



    /*
        @Author : Jorge V
        Guardamos la referencia de la key

       */
    private void saveMovieReference(Long movieId) {
        ContentValues entry = new ContentValues();
        entry.put(MoviesContract.COLUMN_MOVIE_ID_KEY, movieId);

    }

   /*
     @Author : Jorge V
     Guardar la pelicula en db

    */
    private Uri saveMovie(Movie movie) {
        Log.d(LOG_TAG,"Movie".concat(movie.getName()));
        return  context.getContentResolver().insert(MoviesContract.MovieEntry.CONTENT_URI, movie.toContentValues());
    }

    /*
    @Author : Jorge V
    Log de resultados

   */
    private void logResponse(SearchResponse<Movie> discoverMoviesResponse) {
        Log.d(LOG_TAG, "page == " + discoverMoviesResponse.getPage() + " " +
                discoverMoviesResponse.getResults().toString());
    }


    /*
        @Author : Jorge V
       Informar que ha acabado

       */
    private void sendUpdateFinishedBroadcast(boolean successfulUpdated) {
        Intent intent = new Intent(BROADCAST_UPDATE_FINISHED);
        intent.putExtra(EXTRA_IS_SUCCESSFUL_UPDATED, successfulUpdated);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }


}
