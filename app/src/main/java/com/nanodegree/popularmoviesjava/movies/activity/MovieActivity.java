package com.nanodegree.popularmoviesjava.movies.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;

import com.nanodegree.popularmoviesjava.R;
import com.nanodegree.popularmoviesjava.common.ItemClickListener;
import com.nanodegree.popularmoviesjava.common.ScrollRecyclerViewListener;
import com.nanodegree.popularmoviesjava.dto.MovieDTO;
import com.nanodegree.popularmoviesjava.movies.adapter.MovieAdapter;
import com.nanodegree.popularmoviesjava.movies.presenter.MoviePresenter;
import com.nanodegree.popularmoviesjava.movies.view.MovieView;

import javax.inject.Inject;


/**
 * Created by luanfernandes on 31/08/17.
 */
class MovieActivity extends AppCompatActivity implements MovieView, ItemClickListener {


    @Inject
    MoviePresenter presenter;
    private MovieAdapter movieAdapter;
    private ScrollRecyclerViewListener scrollRecyclerViewListener;

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
//                Intent movieDetailIntent = Intent(this, DetailMovieActivity.class)
//                movieDetailIntent.putExtra(DetailMovieActivity.MOVIE_KEY, movie)
//                startActivity(movieDetailIntent);
            }
        });

        GridLayoutManager gridLayoutManager = GridLayoutManager(this, 2)
        movieRecyclerView.layoutManager = gridLayoutManager
        movieRecyclerView.adapter = movieAdapter

        scrollRecyclerViewListener = object : ScrollRecyclerViewListener(gridLayoutManager,
                nextPage = {
                    this@MovieActivity.presenter.loadNextPage();
                }
        ) {}


        movieRecyclerView.addOnScrollListener(scrollRecyclerViewListener)
    }

    private fun setup() =
            DaggerMovieComponent.builder().serviceComponent((applicationContext as PopularMoviesApplication)
                    .serviceComponent)
                    .movieModule(MovieModule(this)).build().inject(this)

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        resetRecyclerMovies()
        when (item.itemId) {
            R.id.popular -> presenter.loadPopularMovies()
            R.id.top_rated -> presenter.loadTopRatedMovies()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_pop_movies, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun loadMovies() {
        resetRecyclerMovies()
        presenter.loadPopularMovies()
    }

    private fun resetRecyclerMovies() {
        movieAdapter.movieList.clear()
        presenter.currentPage = 1
    }


    override fun showResult(movies: List<MovieDTO>) {
        movieAdapter.movieList.addAll(movies)
        movieAdapter.notifyDataSetChanged()
    }

    override fun showProgress() {
        progress.visibility = View.VISIBLE
        movieRecyclerView.visibility = View.GONE

    }

    override fun hideProgress() {
        progress.visibility = View.GONE
        movieRecyclerView.visibility = View.VISIBLE
    }


}