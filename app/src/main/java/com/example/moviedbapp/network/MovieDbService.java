package com.example.moviedbapp.network;

import com.example.moviedbapp.model.MovieDBRepo;
import com.example.moviedbapp.model.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieDbService {

    @GET("/3/search/movie")
    Call<MovieDBRepo>getMovies(
            @Query("api_key")String apiKey,
            @Query("query")String searchMovieString);

    /*@GET("/3/search/movie/{id}")
        Call<Result>getMovie(
                @Path("id")int id,
                @Query("api_key")String apiKey);*/
}
