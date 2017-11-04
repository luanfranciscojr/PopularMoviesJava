package com.nanodegree.popularmoviesjava.common;

import android.support.annotation.StringDef;

import static com.nanodegree.popularmoviesjava.common.SortType.POPULAR;
import static com.nanodegree.popularmoviesjava.common.SortType.TOP_RATED;

/**
 * Created by luan_ on 04/11/2017.
 */

@StringDef({TOP_RATED, POPULAR})
public @interface SortType {

    public static final String TOP_RATED = "top_rated";

    public static final String POPULAR = "popular";
}
