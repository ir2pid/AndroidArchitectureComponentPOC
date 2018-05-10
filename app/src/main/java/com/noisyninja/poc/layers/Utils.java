package com.noisyninja.poc.layers;

import android.util.Log;

import com.google.gson.GsonBuilder;

/**
 * Created by sudiptadutta on 27/04/18.
 */

public class Utils {

    public static void logI(Class clazz, String text) {
        Log.i(clazz.getSimpleName(), text);
    }

    public static String toJson(Object object) {
        return new GsonBuilder().setPrettyPrinting().create().toJson(object);
    }

    public static <T> T fromJson(String string, Class<T> t) {
        return new GsonBuilder().setPrettyPrinting().create().fromJson(string, t);
    }
}