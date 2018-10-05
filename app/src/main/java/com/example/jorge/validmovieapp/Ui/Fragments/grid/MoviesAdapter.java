package com.example.jorge.validmovieapp.Ui.Fragments.grid;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.jorge.validmovieapp.Models.Movie;
import com.example.jorge.validmovieapp.R;
import com.example.jorge.validmovieapp.Util.CursorRecyclerViewAdapter;
import com.example.jorge.validmovieapp.Util.OnItemClickListener;



public class MoviesAdapter extends CursorRecyclerViewAdapter<MovieGridItemViewHolder> {


    private static final String POSTER_IMAGE_BASE_URL = "https://www.gyanwalebaba.com/wp-content/uploads/2018/01/stedwardedge.com_.jpj_.jpg";
    private final Context context;
    private OnItemClickListener onItemClickListener;

    public MoviesAdapter(Context context, Cursor cursor) {
        super(cursor);
        this.context = context;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @Override
    @SuppressLint("PrivateResource")
    public void onBindViewHolder(MovieGridItemViewHolder viewHolder, Cursor cursor) {
        if (cursor != null) {
            Movie movie = Movie.fromCursor(cursor);
            viewHolder.tvTitle.setText(""+movie.getName());
            Glide.with(context)
                    .load(POSTER_IMAGE_BASE_URL)
                    .placeholder(new ColorDrawable(context.getResources().getColor(R.color.accent_material_light)))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .fitCenter()
                    .crossFade()
                    .into(viewHolder.ivImage);
        }

    }

    @Override
    public MovieGridItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MovieGridItemViewHolder(itemView, onItemClickListener);
    }

    @Nullable
    public Movie getItem(int position) {
        Cursor cursor = getCursor();
        if (cursor == null) {
            return null;
        }
        if (position < 0 || position > cursor.getCount()) {
            return null;
        }
        cursor.moveToFirst();
        for (int i = 0; i < position; i++) {
            cursor.moveToNext();
        }
        return Movie.fromCursor(cursor);
    }

}
