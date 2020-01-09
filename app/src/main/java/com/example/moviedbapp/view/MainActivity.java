package com.example.moviedbapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.moviedbapp.R;
import com.example.moviedbapp.adapter.MovieAdapter;
import com.example.moviedbapp.model.MovieDBRepo;
import com.example.moviedbapp.model.Result;
import com.example.moviedbapp.viewmodel.MovieDbViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieItemDelegate{
    private static final String DETAILS = "movie_details";
    private MovieDbViewModel movieDbViewModel;

    Observer<MovieDBRepo> myObserver;

    MovieDetailFragment movieDetailFragment = new MovieDetailFragment();

    @BindView(R.id.movie_search_recyclerview)
    RecyclerView movieSeachRecyclerView;

    @BindView(R.id.movie_search_editview)
    EditText movieSearchEditView;

    @BindView(R.id.movie_search_button)
    Button movieSearchButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        movieDbViewModel = ViewModelProviders.of(this).get(MovieDbViewModel.class);
        myObserver = new Observer<MovieDBRepo>() {

            @Override
            public void onChanged(MovieDBRepo movieDBRepo) {
                setUpRv(movieDBRepo.getResults());
            }



        };
        movieDbViewModel.getMovieResultLiveData().observe(this, myObserver);


        movieSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG_Q", "onClick: inside onclick");
                movieDbViewModel.getMovies(movieSearchEditView.getText().toString());
            }
        });



    }

    void setUpRv(List<Result> results){
        MovieAdapter movieAdapter = new MovieAdapter(results, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        movieSeachRecyclerView.setAdapter(movieAdapter);
        movieSeachRecyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(movieSeachRecyclerView.getContext(),
                layoutManager.getOrientation());
        movieSeachRecyclerView.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public void viewMovieItem(Result result) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(DETAILS, result);
        movieDetailFragment.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(movieDetailFragment.getTag())
                .add(R.id.movie_detail_frame_layout, movieDetailFragment)
                .commit();
    }
}
