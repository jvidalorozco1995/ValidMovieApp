package com.example.jorge.validmovieapp.Ui.Fragments.grid;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.jorge.validmovieapp.Models.Movie;
import com.example.jorge.validmovieapp.R;
import com.example.jorge.validmovieapp.Util.OnItemClickListener;

import java.util.List;



public class MoviesSearchAdapter extends ArrayRecyclerViewAdapter<Movie, MovieGridItemViewHolder> {

    private static final String POSTER_IMAGE_BASE_URL = "https://image.tmdb.org/t/p/";
    private static final String POSTER_IMAGE_SIZE = "w780";

    private Context context;
    private OnItemClickListener onItemClickListener;

    public MoviesSearchAdapter(Context context, @Nullable List<Movie> items) {
        super(items);
        this.context = context;
    }

    @Override
    public MovieGridItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MovieGridItemViewHolder(itemView, onItemClickListener);
    }

    @Override
    @SuppressLint("PrivateResource")
    public void onBindViewHolder(MovieGridItemViewHolder holder, int position) {

        Movie movie = getItems().get(position);
        holder.tvTitle.setText(""+movie.getName());

        Glide.with(context)
                .load(POSTER_IMAGE_BASE_URL + POSTER_IMAGE_SIZE + movie.getPoster_path())
                .placeholder(new ColorDrawable(context.getResources().getColor(R.color.accent_material_light)))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
                .crossFade()
                .into(holder.ivImage);

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }
}
