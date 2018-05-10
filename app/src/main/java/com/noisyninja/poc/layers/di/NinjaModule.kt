package com.noisyninja.poc.layers.di

import android.content.Context
import android.content.res.Resources
import com.noisyninja.poc.NinjaApp
import com.noisyninja.poc.layers.AppExecutors
import com.noisyninja.poc.layers.UtilModule
import com.noisyninja.poc.layers.database.DataBaseModule
import com.noisyninja.poc.layers.database.DataBaseModule2
import com.noisyninja.poc.layers.database.viewmodel.ViewModelFactory
import com.noisyninja.poc.layers.network.HttpClient
import com.noisyninja.poc.layers.network.NetworkModule
import com.squareup.leakcanary.RefWatcher
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Named
import javax.inject.Singleton

/**
 * module containing dependency modules
 * Created by sudiptadutta on 27/04/18.
 */

@Module
class NinjaModule(private val application: NinjaApp, val refWatcher: RefWatcher) {

    @Provides
    @Singleton
    fun provideApplication(): NinjaApp {
        return application
    }

    @Provides
    @Singleton
    fun provideRefWatch(): RefWatcher {
        return refWatcher
    }

    @Provides
    @Singleton
    fun provideViewModelFactory(dataBaseModule: DataBaseModule2): ViewModelFactory {
        return ViewModelFactory(dataBaseModule)
    }

    @Provides
    @Singleton
    fun provideApplicationContext(): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideResources(): Resources {
        return application.resources
    }

    @Provides
    @Singleton
    fun provideHttpClient(context: Context, utilModule: UtilModule): HttpClient {
        return HttpClient(context, utilModule)
    }

    @Provides
    @Singleton
    fun provideNetwork(httpClient: HttpClient): NetworkModule {
        return NetworkModule(httpClient)
    }

    @Provides
    @Singleton
    @Named("diskIOExecutor")
    fun diskIOExecutor(): Executor {
        return Executors.newSingleThreadExecutor()
    }

    @Provides
    @Singleton
    @Named("networkIOExecutor")
    fun networkIOExecutor(): Executor {
        return Executors.newFixedThreadPool(3)
    }

    @Provides
    @Singleton
    fun mainThreadExecutor(): Executor {
        return AppExecutors.MainThreadExecutor()
    }

    @Provides
    @Singleton
    fun provideExecutor(@Named("diskIOExecutor") diskIOExecutor: Executor, @Named("networkIOExecutor") networkIOExecutor: Executor, mainThreadExecutor: AppExecutors.MainThreadExecutor): AppExecutors {
        return AppExecutors(diskIOExecutor, networkIOExecutor, mainThreadExecutor)
    }

    @Provides
    @Singleton
    fun provideDataBase(utilModule: UtilModule, application: NinjaApp): DataBaseModule {
        return DataBaseModule(utilModule, application)
    }

    @Provides
    @Singleton
    fun provideDataBase2(utilModule: UtilModule, application: NinjaApp): DataBaseModule2 {
        return DataBaseModule2(utilModule, application)
    }

    @Provides
    @Singleton
    fun provideUtil(context: Context): UtilModule {
        return UtilModule(context)
    }
}