package com.dgd.earthquakes.screens.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.widget.TextView;

import com.dgd.earthquakes.R;
import com.dgd.earthquakes.models.IQuake;

import java.text.SimpleDateFormat;

/**
 * Created by Max on 02-May-17.
 */

public class QuakeDialog extends Dialog {
    private TextView mDetails;

    public QuakeDialog(@NonNull Context context) {
        super(context);
        init(context);
    }

    protected QuakeDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        init(context);
    }

    protected QuakeDialog(@NonNull Context context, boolean cancelable, @Nullable
            OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init(context);
    }

    private void init(Context context) {
        setContentView(R.layout.dialog_quake);

        setTitle(R.string.quake_time);
        mDetails = (TextView) findViewById(R.id.quakeDetails);
        setCanceledOnTouchOutside(true);
    }

    public void setQuake(IQuake quake){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String dateString = sdf.format(quake.getDate());

        setTitle(dateString);
        mDetails.setText(getContext().getResources().getString(R.string.quake_details, quake.getMagnitude(), quake.getTitle(), quake.getLink()));
    }
}
