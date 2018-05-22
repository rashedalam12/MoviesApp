package com.example.windcore.moviesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsMovie extends AppCompatActivity {


    @BindView(R.id.iv_thumbnail)
    ImageView ivThumbnail;
    @BindView(R.id.tv_release_date)
    TextView tvReleaseDate;
    @BindView(R.id.tv_synopsis)
    TextView tvSynopsis;
    @BindView(R.id.rb_movie_ratting)
    RatingBar rbMovieRatting;
    @BindView(R.id.tv_original_title)
    TextView tvOriginalTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_movie);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        tvOriginalTitle.setText(intent.getStringExtra("OriginalTitle"));
        tvReleaseDate.setText(intent.getStringExtra("ReleaseDate"));
        rbMovieRatting.setRating(Float.parseFloat(intent.getStringExtra("VoteAverage")));
        tvSynopsis.setText(intent.getStringExtra("Synopsis"));
         Picasso.get().load(intent.getStringExtra("PosterPath")).into(ivThumbnail);
    }
}
