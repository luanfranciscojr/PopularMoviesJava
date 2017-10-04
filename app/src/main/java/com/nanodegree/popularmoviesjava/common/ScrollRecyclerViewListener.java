package com.nanodegree.popularmoviesjava.common;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import static android.nfc.tech.MifareUltralight.PAGE_SIZE;

/**
 * Created by luan_ on 03/10/2017.
 */

public class ScrollRecyclerViewListener extends  RecyclerView.OnScrollListener {

    private boolean isLoading;
    private boolean isLastPage;
    private GridLayoutManager layoutManager;
    private LoadMoreItemsListener loadMoreItemsListener;

    public ScrollRecyclerViewListener(GridLayoutManager layoutManager, LoadMoreItemsListener loadMoreItemsListener) {
        this.layoutManager = layoutManager;
        this.loadMoreItemsListener = loadMoreItemsListener;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        int  visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
        int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

        if (!isLoading && !isLastPage) {
            if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                    && firstVisibleItemPosition >= 0
                    && totalItemCount >= PAGE_SIZE) {
                loadMoreItemsListener.loadMoreItems();
            }
        }
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    public void setLastPage(boolean lastPage) {
        isLastPage = lastPage;
    }
}
