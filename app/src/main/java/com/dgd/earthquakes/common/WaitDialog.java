package com.dgd.earthquakes.common;

import android.app.Dialog;
import android.content.Context;
import android.view.WindowManager;
import android.widget.TextView;

import com.dgd.earthquakes.R;

public class WaitDialog extends Dialog {

	public WaitDialog(Context context) {
		super(context, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
		setContentView(R.layout.dialog_wait);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setCancelable(false);
	}

	public void setMessage(String message){
		((TextView)findViewById(R.id.txt_message)).setText(message);
	}
}
