package com.noisyninja.poc.layers.di

import android.content.Context
import android.content.res.Resources
import com.noisyninja.poc.NinjaApp
import com.noisyninja.poc.layers.AppExecutors
import com.noisyninja.poc.layers.UtilModule
import com.noisyninja.poc.layers.database.DataBaseModule
import com.noisyninja.poc.layers.database.DataBaseModule2
import com.noisyninja.poc.layers.database.viewmodel.ViewModelFactory
import com.noisyninja.poc.layers.network.NetworkModule
import com.squareup.leakcanary.RefWatcher
import dagger.Component
import javax.inject.Singleton

/**
 * module interface
 * Created by sudiptadutta on 27/04/18.
 */

@Singleton
@Component(modules = arrayOf(NinjaModule::class))
interface NinjaComponent {

    fun inject(ninjaApplication: NinjaApp)
    fun app(): NinjaApp
    fun refWatcher(): RefWatcher
    fun appContext(): Context
    fun resources(): Resources
    fun network(): NetworkModule
    fun vmf(): ViewModelFactory
    fun database(): DataBaseModule
    fun database2(): DataBaseModule2
    fun executor(): AppExecutors
    fun util(): UtilModule
}