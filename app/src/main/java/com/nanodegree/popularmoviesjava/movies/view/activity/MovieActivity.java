package com.nanodegree.popularmoviesjava.movies.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.nanodegree.popularmoviesjava.PopularMoviesApplication;
import com.nanodegree.popularmoviesjava.R;
import com.nanodegree.popularmoviesjava.common.ItemClickListener;
import com.nanodegree.popularmoviesjava.common.ScrollRecyclerViewListener;
import com.nanodegree.popularmoviesjava.common.SortType;
import com.nanodegree.popularmoviesjava.dto.MovieDTO;
import com.nanodegree.popularmoviesjava.movies.component.DaggerMovieComponent;
import com.nanodegree.popularmoviesjava.movies.module.MovieModule;
import com.nanodegree.popularmoviesjava.movies.presenter.MoviePresenter;
import com.nanodegree.popularmoviesjava.movies.view.MovieView;
import com.nanodegree.popularmoviesjava.movies.view.adapter.MovieAdapter;

import java.util.List;

import javax.inject.Inject;


/**
 * Created by luanfernandes on 31/08/17.
 */
public class MovieActivity extends AppCompatActivity implements MovieView {


    @Inject
    MoviePresenter presenter;
    private MovieAdapter movieAdapter;
    private RecyclerView movieRecyclerView;
    private ProgressBar progress;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setup();
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initViews();
        loadMovies();
    }

    private void initViews() {
        movieAdapter = new MovieAdapter(this, new ItemClickListener() {

            @Override
            public void onItemClickListener(int position) {
                MovieDTO movie = movieAdapter.movieList.get(position);
                Intent movieDetailIntent = new Intent(MovieActivity.this, DetailMovieActivity.class);
                movieDetailIntent.putExtra(DetailMovieActivity.MOVIE_KEY, movie);
                startActivity(movieDetailIntent);
            }
        });
        progress = (ProgressBar) findViewById(R.id.progress);
        movieRecyclerView = (RecyclerView) findViewById(R.id.movieRecyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        movieRecyclerView.setLayoutManager(gridLayoutManager);
        movieRecyclerView.setAdapter(movieAdapter);
        movieRecyclerView.addOnScrollListener(new ScrollRecyclerViewListener(gridLayoutManager) {
            @Override
            protected void nextPage() {
                presenter.loadNextPage();
            }
        });
    }

    private void setup() {
        DaggerMovieComponent.builder().serviceComponent(((PopularMoviesApplication) getApplicationContext()).getServiceComponent())
                .movieModule(new MovieModule(this)).build().inject(this);
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        resetRecyclerMovies();
        switch (item.getItemId()) {
            case R.id.popular:
                presenter.loadMovies(SortType.POPULAR);
                break;
            case R.id.top_rated:
                presenter.loadMovies(SortType.TOP_RATED);
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_pop_movies, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void loadMovies() {
        resetRecyclerMovies();
        presenter.loadMovies(SortType.POPULAR);
    }

    private void resetRecyclerMovies() {
        movieAdapter.movieList.clear();
        presenter.resetCurrentPage();
    }


    public void showResult(List<MovieDTO> movies) {
        movieAdapter.addMovies(movies);

    }

    public void showProgress() {
        progress.setVisibility(View.VISIBLE);
        movieRecyclerView.setVisibility(View.GONE);

    }

    public void hideProgress() {
        progress.setVisibility(View.GONE);
        movieRecyclerView.setVisibility(View.VISIBLE);
    }

}