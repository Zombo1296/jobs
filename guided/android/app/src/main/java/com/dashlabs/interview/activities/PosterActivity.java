package com.dashlabs.interview.activities;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dashlabs.interview.R;

public class PosterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poster);

        //hide default actionBar

        getSupportActionBar().hide();

        // Receive data

        String extraTitle = getIntent().getExtras().getString("extra_title");
        String extraDate = getIntent().getExtras().getString("extra_releaseDate");
        String extraVote = getIntent().getExtras().getString("extra_average");
        String extraImg = getIntent().getExtras().getString("extra_image");
        String extraOverview = getIntent().getExtras().getString("extra_overview");

        //init views

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsinglayout);
        collapsingToolbarLayout.setTitleEnabled(true);

        TextView movie_title = findViewById(R.id.expand_movieTitle);
        TextView release_date = findViewById(R.id.expand_releaseDate);
        TextView vote_average = findViewById(R.id.expand_voteAverage);
        ImageView img_poster = findViewById(R.id.expand_posterImg);
        TextView overview = findViewById(R.id.expand_overview);

        //set values to each view
        movie_title.setText(extraTitle);
        release_date.setText(extraDate);
        vote_average.setText(extraVote);
        overview.setText(extraOverview);

        collapsingToolbarLayout.setTitle(extraTitle);

        //request option
        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

        // set image using Glide
        Glide.with(this).load(extraImg).apply(requestOptions).into(img_poster);
    }
}
