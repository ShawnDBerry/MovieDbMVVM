package com.example.moviedbapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviedbapp.R;
import com.example.moviedbapp.model.Result;
import com.example.moviedbapp.viewmodel.MovieDbViewModel;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>{
    List<Result> resultList;
    MovieItemDelegate movieItemDelegate;
    private Context applicationContext;

    public interface MovieItemDelegate{
        void viewMovieItem(Result result);
    }

    public MovieAdapter(List<Result> resultList, MovieItemDelegate movieItemDelegate) {
        this.resultList = resultList;
        this.movieItemDelegate = movieItemDelegate;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        applicationContext = parent.getContext().getApplicationContext();
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item_layout, parent, false);
        return new MovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, final int position) {
        Log.d("TAG_Q", "onBindViewHolder: " + (holder.movieNameText==null));
        Animation transition = AnimationUtils.loadAnimation(applicationContext, R.anim.transition);
        Animation textTransition = AnimationUtils.loadAnimation(applicationContext, R.anim.text_transition_animation);
        holder.itemView.startAnimation(transition);
        holder.movieNameText.setText(resultList.get(position).getTitle());
        holder.movieReleaseDate.setText(resultList.get(position).getReleaseDate());
        holder.movieNameText.startAnimation(textTransition);
        holder.movieReleaseDate.startAnimation(textTransition);

        Glide.with(applicationContext)
                .load("https://image.tmdb.org/t/p/w1280" + resultList.get(position).getPosterPath())
                .into(holder.movieImage);

        holder.movieImage.setAnimation(transition);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movieItemDelegate.viewMovieItem(resultList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView movieImage;
        TextView movieReleaseDate;
        TextView movieNameText;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            movieImage = itemView.findViewById(R.id.movie_imageview);
            movieNameText = itemView.findViewById(R.id.movie_name_textview);
            movieReleaseDate = itemView.findViewById(R.id.movie_release_date_textview);
        }
    }
}
