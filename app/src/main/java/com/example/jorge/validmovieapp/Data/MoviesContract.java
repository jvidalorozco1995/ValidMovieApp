package com.example.jorge.validmovieapp.Data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

public class MoviesContract {

    public static final String CONTENT_AUTHORITY = "com.example.jorge.validmovieapp.providercontent";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_MOVIES = "movies";

    public static final String PATH_FAVORITES = "favorites";

    public static final String COLUMN_MOVIE_ID_KEY = "movie_id";

    private MoviesContract() {
    }


    /*
          @Author : Jorge V
          Columnas de peliculas, para el proveedor de contenidos
         */
    public static final class MovieEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_MOVIES).build();

        public static final String CONTENT_DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MOVIES;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MOVIES;

        public static final String TABLE_NAME = "movies";

        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_IMAGE = "image";



        public static final String COLUMN_ORIGINAL_TITLE = "title";
        public static final String COLUMN_OVERVIEW = "overview";
        public static final String COLUMN_RATING = "rating";
        public static final String COLUMN_RELEASE_DATE = "releaseDate";

        private static final String[] COLUMNS = {_ID, COLUMN_NAME,COLUMN_IMAGE,COLUMN_ORIGINAL_TITLE,COLUMN_OVERVIEW,COLUMN_RATING,COLUMN_RELEASE_DATE};

        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_NAME + " TEXT, " +
                        COLUMN_IMAGE + " TEXT, " +
                        COLUMN_ORIGINAL_TITLE + " TEXT, " +
                        COLUMN_OVERVIEW + " TEXT, " +
                        COLUMN_RATING + " TEXT, " +
                        COLUMN_RELEASE_DATE + " TEXT " +
                        " );";



        private MovieEntry() {
        }

        public static Uri buildMovieUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static long getIdFromUri(Uri uri) {
            return ContentUris.parseId(uri);
        }

        public static String[] getColumns() {
            return COLUMNS.clone();
        }
    }


    /*
          @Author : Jorge V
          Columnas de peliculas favoritas, para el proveedor de contenidos
         */
    public static final class Favorites implements BaseColumns {
        public static final Uri CONTENT_URI = MovieEntry.CONTENT_URI.buildUpon()
                .appendPath(PATH_FAVORITES)
                .build();
        public static final String CONTENT_DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MOVIES
                        + "/" + PATH_FAVORITES;

        public static final String TABLE_NAME = "favorites";

        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_MOVIE_ID_KEY + " INTEGER NOT NULL, " +

                        " FOREIGN KEY (" + COLUMN_MOVIE_ID_KEY + ") REFERENCES " +
                        MovieEntry.TABLE_NAME + " (" + MovieEntry._ID + ") " +

                        " );";

        private static final String[] COLUMNS = {_ID, COLUMN_MOVIE_ID_KEY};

        private Favorites() {
        }

        public static String[] getColumns() {
            return COLUMNS.clone();
        }
    }
}
