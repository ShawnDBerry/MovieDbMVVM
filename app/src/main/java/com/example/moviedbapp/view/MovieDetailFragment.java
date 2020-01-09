package com.example.moviedbapp.view;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.moviedbapp.R;
import com.example.moviedbapp.model.Result;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MovieDetailFragment extends Fragment {

    @BindView(R.id.movie_imageview)
    ImageView movieImage;

    @BindView(R.id.detail_movie_name_textview)
    TextView detailMovieName;

    @BindView(R.id.detail_movie_release_date_textview)
    TextView detailReleaseDate;

    @BindView(R.id.detail_movie_overview_textview)
    TextView detailMovieOverview;

    @BindView(R.id.close_icon_imageview)
    ImageView closeFragmentIcon;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.movie_detail_fragment, container, false);

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        Bundle bundle = this.getArguments();

        Result myResult = bundle.getParcelable("movie_details");

            Log.d("TAG", "onViewCreated: " + myResult);

        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w1280" + myResult.getPosterPath())
                .into(movieImage);
        detailMovieName.setText(myResult.getTitle());
        detailReleaseDate.setText(myResult.getReleaseDate());
        detailMovieOverview.setText(myResult.getOverview());

    }

    @OnClick(R.id.close_icon_imageview)
    public void closeFragment(View view) {
        getActivity().getSupportFragmentManager().popBackStack();
        Log.d("TAG_X", "WHere you at?");
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }
}
