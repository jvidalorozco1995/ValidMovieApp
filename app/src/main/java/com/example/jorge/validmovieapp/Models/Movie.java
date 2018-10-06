package com.example.jorge.validmovieapp.Models;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.jorge.validmovieapp.Data.MoviesContract;
import com.google.gson.annotations.SerializedName;

/*
@Author: Jorge V
Clase Modelo peliculas
*/
public class Movie implements Parcelable {


    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("poster_path")
    private String poster_path;

    @SerializedName("original_title")
    private String originalTitle;

    @SerializedName("overview")
    private String overview;

    @SerializedName("vote_average")
    private String rating;

    @SerializedName("release_date")
    private String releaseDate;



    public Movie(int id, String name, String posterPath) {
        this.id = id;
        this.name = name;
        this.poster_path = posterPath;
    }

    protected Movie(Parcel in) {
        this.setId(in.readInt());
        this.setName(in.readString());
        this.setPoster_path(in.readString());
        this.setOriginalTitle(in.readString());
        this.setOverview(in.readString());
        this.setRating(in.readString());
        this.setReleaseDate(in.readString());
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };


    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(MoviesContract.MovieEntry._ID, id);
        values.put(MoviesContract.MovieEntry.COLUMN_NAME, name);
        values.put(MoviesContract.MovieEntry.COLUMN_IMAGE, poster_path);
        values.put(MoviesContract.MovieEntry.COLUMN_ORIGINAL_TITLE, originalTitle);
        values.put(MoviesContract.MovieEntry.COLUMN_OVERVIEW, overview);
        values.put(MoviesContract.MovieEntry.COLUMN_RATING, rating);
        values.put(MoviesContract.MovieEntry.COLUMN_RELEASE_DATE, releaseDate);


        return values;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.getId());
        dest.writeString(this.getName());
        dest.writeString(this.getPoster_path());
        dest.writeString(this.getOriginalTitle());
        dest.writeString(this.getOverview());
        dest.writeString(this.getRating());
        dest.writeString(this.getReleaseDate());

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Movie fromCursor(Cursor cursor) {

        int id = cursor.getInt(cursor.getColumnIndex(MoviesContract.MovieEntry._ID));
        String title = cursor.getString(cursor.getColumnIndex(MoviesContract.MovieEntry.COLUMN_NAME));
        String poster = cursor.getString(cursor.getColumnIndex(MoviesContract.MovieEntry.COLUMN_IMAGE));

        Movie movie = new Movie(id, title, poster);
        movie.setId(cursor.getInt(cursor.getColumnIndex(MoviesContract.MovieEntry._ID)));
        movie.setName(cursor.getString(cursor.getColumnIndex(MoviesContract.MovieEntry.COLUMN_NAME)));
        movie.setPoster_path(cursor.getString(cursor.getColumnIndex(MoviesContract.MovieEntry.COLUMN_IMAGE)));
        movie.setOverview(cursor.getString(cursor.getColumnIndex(MoviesContract.MovieEntry.COLUMN_OVERVIEW)));
        movie.setReleaseDate(cursor.getString(cursor.getColumnIndex(MoviesContract.MovieEntry.COLUMN_RELEASE_DATE)));
        movie.setRating(cursor.getString(cursor.getColumnIndex(MoviesContract.MovieEntry.COLUMN_RATING)));
        movie.setOriginalTitle(cursor.getString(cursor.getColumnIndex(MoviesContract.MovieEntry.COLUMN_ORIGINAL_TITLE)));


        return movie;

    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }
}
