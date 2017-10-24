package com.nanodegree.popularmoviesjava.common;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by luan_ on 03/10/2017.
 */

public abstract class ScrollRecyclerViewListener extends  RecyclerView.OnScrollListener {

    private GridLayoutManager layoutManager;

    public ScrollRecyclerViewListener(GridLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (dy > 0 && this.layoutManager.findLastVisibleItemPosition() == this.layoutManager.getItemCount() - 1) {
            nextPage();
        }
    }

    protected abstract void nextPage();



}
