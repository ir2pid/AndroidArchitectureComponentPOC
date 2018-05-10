package com.noisyninja.poc.view.custom;

import android.databinding.BindingAdapter;
import android.view.View;

import com.noisyninja.poc.R;

/**
 * Created by sudiptadutta on 29/04/18.
 */

public class DataBindings {

    @BindingAdapter("isOccupied")
    public static void convertBooleanToTint(View view, boolean isOccupied) {
        if (isOccupied) {
            ((IconTextView) view).setText(R.string.bookmark);
            ((IconTextView) view).setTextColor(view.getContext().getResources().getColor(R.color.colorAccent));
        } else {
            ((IconTextView) view).setText(R.string.unbookmark);
            ((IconTextView) view).setTextColor(view.getContext().getResources().getColor(R.color.colorPrimary));
        }
    }
}
