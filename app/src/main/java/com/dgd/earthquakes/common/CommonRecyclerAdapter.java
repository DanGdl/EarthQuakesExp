package com.dgd.earthquakes.common;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dan on 20/08/2017.
 */

public abstract class CommonRecyclerAdapter<T> extends RecyclerView.Adapter<CommonViewHolder<T>> {
    protected final IOnItemClickListener<T> listener;
    protected final Context context;
    protected List<T> items = new ArrayList<>();
    protected final LayoutInflater inflater;
    private boolean hasMoreRows = false;
    protected boolean empty = false;

    public CommonRecyclerAdapter(Context context, IOnItemClickListener<T> listener){
        this.listener = listener;
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public void onBindViewHolder(CommonViewHolder<T> holder, int position) {
        holder.setItem(items.get(position), position);
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public void setItems(List<T> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public void addItems(List<T> items) {
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    public T getItem(int position) {
        int count = getItemCount();
        if(count == 0 || count <= position) {
            return null;
        }
        return items.get(position);
    }

    public List<T> getItems() {
        return items;
    }

    public boolean hasMoreRows(){return hasMoreRows;}

    public void setHasMoreRows(boolean has_more_rows){this.hasMoreRows = has_more_rows;}

    public void setItems(List<T> list, Boolean empty) {
        items = list;
        this.empty = empty;
        notifyDataSetChanged();
    }

    public interface IOnItemClickListener<T> {
        void onItemClicked(T item, int position);
    }
}
