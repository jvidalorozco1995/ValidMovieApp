package com.example.jorge.validmovieapp.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.jorge.validmovieapp.Models.Movie;

import javax.inject.Inject;

/*
@Author: Jorge V
Clase Modelo favoritos, para guardar en db.
*/
public class FavoritesService {

    private final Context context;

    @Inject
    public FavoritesService(Context context) {
        this.context = context.getApplicationContext();
    }

    /*
      @Author : Jorge V
      Metodo para guardar una pelicula como favorita en DB
     */
    public void addToFavorites(Movie movie) {
        context.getContentResolver().insert(MoviesContract.MovieEntry.CONTENT_URI, movie.toContentValues());
        ContentValues contentValues = new ContentValues();
        contentValues.put(MoviesContract.COLUMN_MOVIE_ID_KEY, movie.getId());
        context.getContentResolver().insert(MoviesContract.Favorites.CONTENT_URI, contentValues);
    }

    /*
          @Author : Jorge V
          Metodo para remover la pelicula que fue guardada como favorita
         */
    public void removeFromFavorites(Movie movie) {
        context.getContentResolver().delete(
                MoviesContract.Favorites.CONTENT_URI,
                MoviesContract.COLUMN_MOVIE_ID_KEY + " = " + movie.getId(),
                null
        );
    }

    /*
          @Author : Jorge V
          Metodo para validar si la pelicula es favorita
         */
    public boolean isFavorite(Movie movie) {
        boolean favorite = false;
        Cursor cursor = context.getContentResolver().query(
                MoviesContract.Favorites.CONTENT_URI,
                null,
                MoviesContract.COLUMN_MOVIE_ID_KEY + " = " + movie.getId(),
                null,
                null
        );
        if (cursor != null) {
            favorite = cursor.getCount() != 0;
            cursor.close();
        }
        return favorite;
    }
}
