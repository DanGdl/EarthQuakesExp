package com.dgd.earthquakes.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dgd.earthquakes.R;
import com.dgd.earthquakes.databinding.QuakeDetailsBinding;
import com.dgd.earthquakes.models.IQuake;
import com.dgd.earthquakes.screens.dialogs.QuakeDialog;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by Max on 01-May-17.
 */
public class EarthQuakesAdapter extends RecyclerView.Adapter<EarthQuakesAdapter.QuakeVH> {
    private static SimpleDateFormat mSDF = new SimpleDateFormat("HH:mm, yyyy.MM.dd", Locale.getDefault());
    private Context mContext;
    private List<IQuake> mQuakes;

    public EarthQuakesAdapter(Context context) {
        mContext = context;
    }

    @Override
    public QuakeVH onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        QuakeDetailsBinding cardBinding = DataBindingUtil.inflate(inflater, R.layout.quake_details, parent, false);
        return new QuakeVH(cardBinding);
    }

    @Override
    public void onBindViewHolder(QuakeVH holder, int position) {
        IQuake q = mQuakes.get(position);
        holder.mBinding.quakeTime.setText(mSDF.format(q.getDate()));
        holder.mBinding.setQuake(q);
    }

    @Override
    public int getItemCount() {
        return mQuakes == null ? 0 : mQuakes.size();
    }

    public void setQuakes(List<IQuake> earthQuakes) {
        mQuakes = earthQuakes;
        notifyItemChanged(1);
        notifyDataSetChanged();
    }

    public void addQuakes(List<IQuake> quakes) {
        int start = mQuakes.size();
        mQuakes.addAll(quakes);
        notifyItemRangeInserted(start, quakes.size());
    }

    public long getLastDate() {
        return mQuakes.get(getItemCount() - 1).getDate().getTime();
    }

    class QuakeVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        private QuakeDetailsBinding mBinding;

        QuakeVH(QuakeDetailsBinding itemView) {
            super(itemView.getRoot());
            mBinding = itemView;
            itemView.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            IQuake q = mQuakes.get(getAdapterPosition());
            QuakeDialog dialog = new QuakeDialog(mContext);
            dialog.setQuake(q);
            dialog.show();
        }
    }
}
