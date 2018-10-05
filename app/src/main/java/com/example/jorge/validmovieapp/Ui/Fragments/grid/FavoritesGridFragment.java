package com.example.jorge.validmovieapp.Ui.Fragments.grid;

import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.example.jorge.validmovieapp.Data.MoviesContract;


public class FavoritesGridFragment extends AbstractMoviesGridFragment {

    public static FavoritesGridFragment create() {
        return new FavoritesGridFragment();
    }

    @Override
    @NonNull
    protected Uri getContentUri() {
        return MoviesContract.Favorites.CONTENT_URI;
    }

    @Override
    protected void onCursorLoaded(Cursor data) {
        getAdapter().changeCursor(data);
    }

    @Override
    protected void onRefreshAction() {

        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    protected void onMoviesGridInitialisationFinished() {

    }
}
