package com.nanodegree.popularmoviesjava.movies.view.activity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.nanodegree.popularmoviesjava.PopularMoviesApplication;
import com.nanodegree.popularmoviesjava.R;
import com.nanodegree.popularmoviesjava.dto.MovieDTO;
import com.nanodegree.popularmoviesjava.dto.MovieDetailDTO;
import com.nanodegree.popularmoviesjava.movies.component.DaggerDetailMovieComponent;
import com.nanodegree.popularmoviesjava.movies.module.DetailMovieModule;
import com.nanodegree.popularmoviesjava.movies.presenter.DetailMoviePresenter;
import com.nanodegree.popularmoviesjava.movies.view.DetailMovieView;
import com.nanodegree.popularmoviesjava.service.module.ServiceModule;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.Calendar;

import javax.inject.Inject;


public class DetailMovieActivity extends AppCompatActivity implements DetailMovieView {


    @Inject
    DetailMoviePresenter presenter;
    private final String imageWidth = "w500/";
    public static final String MOVIE_KEY = "MOVIE_KEY";
    private CollapsingToolbarLayout collapsingToolbarLayout;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setup();
        setContentView(R.layout.activity_detail_movie);
        initViews();
        MovieDTO movie = getIntent().getParcelableExtra(MOVIE_KEY);
        presenter.loadMovieDetail(movie.getId());
        showInformations(movie);
    }

    private void initViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

    }

    private void showInformations(MovieDTO movie) {
        collapsingToolbarLayout.setTitle(movie.getOriginalTitle());
        ImageView imageMovie = (ImageView) findViewById(R.id.imageMovie);
        Picasso.with(this).load(ServiceModule.BASE_IMAGE_URL + "w342/" + movie.getPosterPath())
                .placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(imageMovie);

    }

    private void setup() {
        DaggerDetailMovieComponent.builder().serviceComponent(((PopularMoviesApplication)getApplication())
                .getServiceComponent()).detailMovieModule(new DetailMovieModule(this)).build().inject(this);

    }


    public void showDetailMovie(MovieDetailDTO movieDetail) {
        Picasso.with(this)
                .load(ServiceModule.BASE_IMAGE_URL + imageWidth + movieDetail.getBackdropPath())
                .into(new Target() {

                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            DetailMovieActivity.this.collapsingToolbarLayout.setBackground(bitmapDrawable);
                        } else {
                            DetailMovieActivity.this.collapsingToolbarLayout.setBackgroundDrawable(bitmapDrawable);
                        }
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });
        TextView overview = (TextView) findViewById(R.id.overview);
        TextView year = (TextView) findViewById(R.id.year);
        TextView runtime = (TextView) findViewById(R.id.runtime);
        TextView voteAverage = (TextView) findViewById(R.id.voteAverage);
        overview.setText(movieDetail.getOverview());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(movieDetail.getReleaseDate());
        year.setText(String.valueOf(calendar.get(Calendar.YEAR)));
        runtime.setText(getResources().getString(R.string.detail_runtime, movieDetail.getRuntime().toString()));
        voteAverage.setText(getResources().getString(R.string.detail_vote, movieDetail.getVoteAverage().toString()));
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}