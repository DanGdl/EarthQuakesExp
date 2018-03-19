package com.dgd.earthquakes.common;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Dan
 * on 20/08/2017.
 */

public abstract class CommonViewHolder<T> extends RecyclerView.ViewHolder {

    public CommonViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void setItem(T item, int position);
}
