package com.noisyninja.poc.view.custom;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

/**
 * class to show icon graphics in textview
 * Created by sudiptadutta on 29/04/18.
 */

public class IconTextView extends TextView {
    private Context context;

    public IconTextView(Context context) {
        super(context);
        this.context = context;
        createView();
    }

    public IconTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        createView();
    }

    public IconTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        createView();
    }

    private void createView() {
        setGravity(Gravity.CENTER);

        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fontawesome.ttf");
        setTypeface(tf, 1);
    }
}