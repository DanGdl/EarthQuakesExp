package com.dgd.earthquakes.ui.quakes;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.dgd.earthquakes.R;
import com.dgd.earthquakes.models.IQuake;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by Max
 * on 02-May-17.
 */

public class QuakeDialog extends Dialog {
    private TextView mDetails;

    public QuakeDialog(@NonNull Context context) {
        super(context);
        init();
    }

    private void init() {
        setContentView(R.layout.dialog_quake);

        setTitle(R.string.quake_time);
        mDetails = (TextView) findViewById(R.id.quakeDetails);
        setCanceledOnTouchOutside(true);
    }

    public void setQuake(IQuake quake){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
        String dateString = sdf.format(quake.getDate());

        setTitle(dateString);
        mDetails.setText(getContext().getResources().getString(R.string.quake_details, quake.getMagnitude(), quake.getTitle(), quake.getLink()));
    }
}
