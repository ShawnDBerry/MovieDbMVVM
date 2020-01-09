package com.example.moviedbapp.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.moviedbapp.model.MovieDBRepo;
import com.example.moviedbapp.model.Result;
import com.example.moviedbapp.network.MovieDbRetroFitInstance;

import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDbViewModel extends AndroidViewModel {
    private MovieDbRetroFitInstance movieDbRetroFitInstance = new MovieDbRetroFitInstance();

    private MutableLiveData<MovieDBRepo> movieResultLiveData = new MutableLiveData<>();
    /*private MutableLiveData<Result> movieLiveDataById = new MutableLiveData<>();*/

    public MovieDbViewModel(@NonNull Application application) {
        super(application);
    }

    public void getMovies(String searchMovieString){

        movieDbRetroFitInstance.getMovies(searchMovieString).enqueue(new Callback<MovieDBRepo>() {
            @Override
            public void onResponse(Call<MovieDBRepo> call, Response<MovieDBRepo> response) {
                Log.d("TAG_Q", "onResponse: inside movieViewModel");
                movieResultLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<MovieDBRepo> call, Throwable t) {
                Log.d("TAG_Q", "inside onFailure " + t.getMessage());
            }
        });
    }

    public MutableLiveData<MovieDBRepo> getMovieResultLiveData(){
        return movieResultLiveData;
    }

    /*public void getMovie(int id){
        movieDbRetroFitInstance.getMovie(id).enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                movieLiveDataById.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });
    }*/

    /*public MutableLiveData<Result> getmovieLiveDataById(){
        return movieLiveDataById;
    }*/
}
