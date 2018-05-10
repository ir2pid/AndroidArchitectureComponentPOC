package com.noisyninja.poc.model;

import android.databinding.BaseObservable;

import com.noisyninja.poc.layers.Utils;

/**
 * Created by sudiptadutta on 27/04/18.
 */

public class BaseDTO extends BaseObservable {
    @Override
    public String toString() {
        return Utils.toJson(this);
    }
}

