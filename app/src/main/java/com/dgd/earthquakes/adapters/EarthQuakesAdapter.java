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
import com.dgd.earthquakes.models.Quake;
import com.dgd.earthquakes.screens.dialogs.QuakeDialog;

import java.text.SimpleDateFormat;
import java.util.Locale;

import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * Created by Max on 01-May-17.
 */
public class EarthQuakesAdapter extends RecyclerView.Adapter<EarthQuakesAdapter.QuakeVH> implements RealmChangeListener<RealmResults<Quake>> {
    private static SimpleDateFormat mSDF = new SimpleDateFormat("HH:mm, yyyy.MM.dd", Locale.getDefault());
    private Context context;
    private RealmResults<Quake> quakes;

    public EarthQuakesAdapter(Context context) {
        this.context = context;
    }

    @Override
    public QuakeVH onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        QuakeDetailsBinding cardBinding = DataBindingUtil.inflate(inflater, R.layout.quake_details, parent, false);
        return new QuakeVH(cardBinding);
    }

    @Override
    public void onBindViewHolder(QuakeVH holder, int position) {
        IQuake q = quakes.get(position);
        holder.mBinding.quakeTime.setText(mSDF.format(q.getDate()));
        holder.mBinding.setQuake(q);
    }

    @Override
    public int getItemCount() {
        return quakes == null ? 0 : quakes.size();
    }

    public void setQuakes(RealmResults<Quake> earthQuakes) {
        quakes = earthQuakes;
        notifyItemChanged(1);
        notifyDataSetChanged();
        quakes.addChangeListener(this);
    }

    public long getLastDate() {
        return quakes.get(getItemCount() - 1).getDate().getTime();
    }

    @Override
    public void onChange(RealmResults<Quake> quakes) {
        notifyDataSetChanged();
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
            IQuake q = quakes.get(getAdapterPosition());
            QuakeDialog dialog = new QuakeDialog(context);
            dialog.setQuake(q);
            dialog.show();
        }
    }
}
