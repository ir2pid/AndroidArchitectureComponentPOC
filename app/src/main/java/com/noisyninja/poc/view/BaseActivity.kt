package com.noisyninja.poc.view

import android.support.v7.app.AppCompatActivity
import com.noisyninja.poc.layers.di.NinjaInjector.ninjaComponent


/**
 * Base activity for inheritance
 * Created by sudiptadutta on 10/05/18.
 */
open class BaseActivity : AppCompatActivity() {

    public override fun onDestroy() {
        super.onDestroy()
        ninjaComponent.refWatcher().watch(this)
    }
}