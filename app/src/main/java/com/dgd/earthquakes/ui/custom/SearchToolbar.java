package com.dgd.earthquakes.ui.custom;

import android.annotation.TargetApi;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.dgd.earthquakes.R;
import com.dgd.earthquakes.databinding.ToolbarBinding;

/**
 * Created by max on 11/8/17.
 */

public class SearchToolbar extends LinearLayout {
    private ToolbarBinding toolbarBinding;

    public SearchToolbar(Context context) {
        super(context);
        init(context);
    }

    public SearchToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SearchToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(21)
    public SearchToolbar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        toolbarBinding = DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.toolbar, this, true);
    }

    public void setOnSearchClickListener(View.OnClickListener listener){
        toolbarBinding.searchBtn.setOnClickListener(listener);
    }

    public String getQuery(){
        return toolbarBinding.search.getText().toString();
    }
}
