package com.example.moviedbapp.network;

import android.util.Log;

import com.example.moviedbapp.model.MovieDBRepo;
import com.example.moviedbapp.model.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieDbRetroFitInstance {
    public static final String API_KEY = "609f3934d8318546dfe36d68005e4862";
    private final String BASE_URL = "https://api.themoviedb.org/3/search/movie/";


    public MovieDbRetroFitInstance() {
        this.movieDbService = createMovieDbService(getInstance());
    }

    private MovieDbService movieDbService;

    private Retrofit getInstance(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private MovieDbService createMovieDbService(Retrofit retrofit){
        return retrofit.create(MovieDbService.class);
    }

    public Call<MovieDBRepo>getMovies(String searchMovieString){
        Log.d("TAG_Q", "getMovies: inside MovieDbRetrofitInstance");
        return movieDbService.getMovies(API_KEY, searchMovieString);
    }

    /*public Call<Result>getMovie(int id){
        return movieDbService.getMovie(id, API_KEY);
    }*/


}
