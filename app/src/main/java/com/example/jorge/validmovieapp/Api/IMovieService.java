package com.example.jorge.validmovieapp.Api;

import com.example.jorge.validmovieapp.Models.Movie;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface IMovieService {

    /*
              @Author : Jorge V
              Obtener pelicula por id
             */
    @GET("movie/{movie_id}")
    Observable<Movie> getDetailMovies(@Path("movie_id") int movieId);

    /*
              @Author : Jorge V
              Obtener peliculas por busqueda
             */
    @GET("search/keyword")
    Observable<SearchResponse<Movie>> searchMovies(@Query("query") String
                                                           query, @Query("page") Integer page);

}
