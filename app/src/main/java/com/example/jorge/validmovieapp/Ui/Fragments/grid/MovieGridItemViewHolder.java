package com.example.jorge.validmovieapp.Ui.Fragments.grid;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jorge.validmovieapp.R;
import com.example.jorge.validmovieapp.Util.OnItemClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieGridItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.tvTitle)
    TextView tvTitle;

    @BindView(R.id.ivImage)
    ImageView ivImage;


    private OnItemClickListener onItemClickListener;

    public MovieGridItemViewHolder(View itemView, @Nullable OnItemClickListener onItemClickListener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.onItemClickListener = onItemClickListener;
        itemView.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(view, getAdapterPosition());
        }
    }
}
