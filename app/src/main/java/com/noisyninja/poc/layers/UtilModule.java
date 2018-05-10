package com.noisyninja.poc.layers;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

import javax.inject.Inject;

import timber.log.Timber;

import static android.content.Context.MODE_PRIVATE;

/**
 * Module to hold all utility methods
 * Created by sudiptadutta on 27/04/18.
 */
public class UtilModule {
    private static String DEFAULT_PREF = "DEFAULT_PREF";

    private Context mContext;

    @Inject
    public UtilModule(Context context) {

        Utils.logI(this.getClass(), "DataBaseModule2");
        this.mContext = context;
    }

    public String getStringRes(@StringRes int resId) {
        return mContext.getString(resId);
    }

    public String getStringPref(String key) {
        return mContext.getSharedPreferences(DEFAULT_PREF, MODE_PRIVATE).getString(key, null);
    }

    public void setStringPref(String key, String value) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(DEFAULT_PREF, MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.apply();
    }

    public void deleteStringPref(String key) {
        mContext.getSharedPreferences(DEFAULT_PREF, MODE_PRIVATE).edit().remove(key).apply();
    }

    public void showSnackBar(View view, String text) {
        Snackbar snackbar = Snackbar.make(view, text, Snackbar.LENGTH_SHORT);
        ViewGroup contentLay = (ViewGroup) snackbar.getView().findViewById(android.support.design.R.id.snackbar_text).getParent();
        ProgressBar item = new ProgressBar(view.getContext());
        contentLay.addView(item);
        snackbar.show();
        //.setAction("Action", null).show();
    }

    public void logI(String text) {
        Timber.i(text);
    }

    public void toast(String string) {
        Toast.makeText(mContext, string,
                Toast.LENGTH_SHORT).show();
    }

    public String toJson(Object object) {
        return new GsonBuilder().setPrettyPrinting().create().toJson(object);
    }

    public <T> T fromJson(String string, Class<T> t) {
        return new GsonBuilder().setPrettyPrinting().create().fromJson(string, t);
    }

    public <T> T fromJson(String string, Type type) {
        return new GsonBuilder().setPrettyPrinting().create().fromJson(string, type);
    }

    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm != null && cm.getActiveNetworkInfo() != null;
    }
/*
    public void loadImage(Context context, String url, ImageView imageView) {
        String imageUrl = BuildConfig.POSTER_URI + url;
        //Utils.logI(Utils.class, "Loading image:" + imageUrl);
        Picasso.with(context)
                .load(imageUrl)
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round)
                .into(imageView);
    }

    public void share(Context context, String name, String title) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType(BuildConfig.MIME_TYPE);
        intent.putExtra(Intent.EXTRA_SUBJECT, title);
        intent.putExtra(Intent.EXTRA_TEXT, getStringRes(context, R.string.share_tag) + name);
        context.startActivity(intent);
    }

    public void getAllShared(Context context) {
        Map<String, ?> allEntries = context.getSharedPreferences(DEFAULT_PREF, MODE_PRIVATE).getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            Log.d("map values", entry.getKey() + ": " + entry.getValue().toString());
        }
    }
*/

}