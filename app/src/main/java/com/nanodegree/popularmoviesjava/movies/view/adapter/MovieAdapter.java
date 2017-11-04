package com.nanodegree.popularmoviesjava.movies.view.adapter;


import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nanodegree.popularmoviesjava.R;
import com.nanodegree.popularmoviesjava.common.ItemClickListener;
import com.nanodegree.popularmoviesjava.dto.MovieDTO;
import com.nanodegree.popularmoviesjava.service.module.ServiceModule;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private final ItemClickListener onClickItem;
    private final Context context;
    private String imageWidth = "w500/";
    public ArrayList<MovieDTO> movieList = new ArrayList<>();

    public MovieAdapter(Context context, ItemClickListener onClickItem) {
        this.onClickItem = onClickItem;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        MovieDTO movie = movieList.get(position);
        holder.itemView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onClickItem.onItemClickListener(holder.getAdapterPosition());
                    }
                }
        );
        Picasso.with(context).load(ServiceModule.BASE_IMAGE_URL + imageWidth + movie.getPosterPath())
                .placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(holder.movieImage);
    }

    public int getItemCount() {
        return movieList.size();
    }

    public void addMovies(List<MovieDTO> movies) {
        movieList.addAll(movies);
        notifyDataSetChanged();
    }


    class ViewHolder extends RecyclerView.ViewHolder

    {

        AppCompatImageView movieImage;

        public ViewHolder(View itemView) {
            super(itemView);
            movieImage = (AppCompatImageView) itemView.findViewById(R.id.imageMovie);
        }

    }
}
