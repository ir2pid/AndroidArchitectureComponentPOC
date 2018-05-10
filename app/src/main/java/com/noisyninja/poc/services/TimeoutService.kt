package com.noisyninja.poc.services

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import com.noisyninja.poc.BuildConfig.APPLICATION_ID
import com.noisyninja.poc.BuildConfig.TIMER
import com.noisyninja.poc.layers.Utils
import com.noisyninja.poc.layers.di.NinjaInjector.ninjaComponent
import com.noisyninja.poc.model.Table
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

/**
 * Service to count timeout and clear reservations
 * Created by sudiptadutta on 01/05/18.
 */

class TimeoutService : Service() {

    internal lateinit var context: Context
    private var timer: Timer? = null

    override fun onBind(arg0: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        // cancel if already existed
        if (timer != null) {
            timer?.cancel()
        } else {
            // recreate new
            timer = Timer()
        }
        // schedule task
        timer?.scheduleAtFixedRate(Task(), TIMER.toLong(), TIMER.toLong())

        Utils.logI(TimeoutService::class.java, "Service started ...")
        Utils.logI(TimeoutService::class.java, "Timer started ...")
    }

    override fun onDestroy() {
        super.onDestroy()
        timer?.cancel()
        Utils.logI(TimeoutService::class.java, "Service stopped ...")
        Utils.logI(TimeoutService::class.java, "Timer stopped ...")
        ninjaComponent.refWatcher().watch(this)
    }

    private inner class Task : TimerTask() {
        override fun run() {

            ninjaComponent.util().logI("resetting reservations timeout")
            ninjaComponent.database().allTable
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe { list: List<Table> ->
                        if (!list.isEmpty()) {
                            for (table: Table in list) {
                                table.isOccupied = false
                                table.customerID = -1
                            }

                            ninjaComponent.database().insertAllTable(list)

                            val intent = Intent()
                            intent.action = APPLICATION_ID
                            sendBroadcast(intent)
                        }
                    }

        }
    }

}