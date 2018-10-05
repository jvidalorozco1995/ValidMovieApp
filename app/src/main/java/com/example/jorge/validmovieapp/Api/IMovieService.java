package com.example.jorge.validmovieapp.Api;

import com.example.jorge.validmovieapp.Models.Movie;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface IMovieService {


    @GET("search/keyword")
    Observable<SearchResponse<Movie>> searchMovies(@Query("query") String
                                                           query, @Query("page") Integer page);

}
