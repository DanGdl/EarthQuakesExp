package com.dgd.earthquakes.ui.customs;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ToggleButton;

/**
 * Created by dan
 * on 21/03/2016.
 */
public class SquareToggleButton extends ToggleButton{

    public SquareToggleButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SquareToggleButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareToggleButton(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final int width = getMeasuredWidth();
        final int height = getMeasuredHeight();
        if(width < height) {
            setMeasuredDimension(width, width);
        }else
            setMeasuredDimension(height, height);
    }
}
