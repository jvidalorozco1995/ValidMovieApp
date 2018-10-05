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


    public Movie(int id, String name) {
        this.id = id;
        this.name = name;
    }

    protected Movie(Parcel in) {
        this.setId(in.readInt());
        this.setName(in.readString());
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
        Movie movie = new Movie(id, title);
        movie.setId(cursor.getInt(cursor.getColumnIndex(MoviesContract.MovieEntry._ID)));
        movie.setName(
                cursor.getString(cursor.getColumnIndex(MoviesContract.MovieEntry.COLUMN_NAME)));

        return movie;

    }
}
