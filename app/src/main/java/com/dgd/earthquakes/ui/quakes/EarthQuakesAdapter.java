package com.dgd.earthquakes.ui.quakes;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.View;
import android.view.ViewGroup;

import com.dgd.earthquakes.R;
import com.dgd.earthquakes.common.CommonRecyclerAdapter;
import com.dgd.earthquakes.common.CommonViewHolder;
import com.dgd.earthquakes.databinding.QuakeDetailsBinding;
import com.dgd.earthquakes.models.Quake;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by Max on 01-May-17.
 */
public class EarthQuakesAdapter extends CommonRecyclerAdapter<Quake> {
    private static SimpleDateFormat mSDF = new SimpleDateFormat("HH:mm, yyyy.MM.dd", Locale.getDefault());

    public EarthQuakesAdapter(Context context, IOnItemClickListener<Quake> listener) {
        super(context, listener);
    }

    @Override
    public QuakeVH onCreateViewHolder(ViewGroup parent, int viewType) {
        QuakeDetailsBinding cardBinding = DataBindingUtil.inflate(inflater, R.layout.quake_details, parent, false);
        return new QuakeVH(cardBinding);
    }

    public long getLastDate() {
        if(items != null && !items.isEmpty()) {
            return items.get(getItemCount() - 1).getDate().getTime();
        }
        else{
            return -1;
        }
    }

    private class QuakeVH extends CommonViewHolder<Quake> implements View.OnClickListener {
        final QuakeDetailsBinding mBinding;

        QuakeVH(QuakeDetailsBinding itemView) {
            super(itemView.getRoot());
            mBinding = itemView;
            itemView.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(listener != null){
                int position = getAdapterPosition();
                listener.onItemClicked(items.get(position), position);
            }
        }

        @Override
        public void setItem(Quake q, int position) {
            mBinding.quakeTime.setText(mSDF.format(q.getDate()));
            mBinding.setQuake(q);
        }
    }
}
