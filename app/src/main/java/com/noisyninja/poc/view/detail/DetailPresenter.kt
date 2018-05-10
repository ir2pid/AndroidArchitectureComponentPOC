package com.noisyninja.poc.view.detail

import android.view.View
import com.noisyninja.poc.R
import com.noisyninja.poc.layers.di.NinjaComponent
import com.noisyninja.poc.layers.network.ICallback
import com.noisyninja.poc.model.Table
import com.noisyninja.poc.view.interfaces.IDetailActivity
import com.noisyninja.poc.view.interfaces.IDetailPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Detail presenter
 * Created by sudiptadutta on 28/04/18.
 */

class DetailPresenter internal constructor(private val iDetailActivity: IDetailActivity, private val ninjaComponent: NinjaComponent?, private val customerID: Int) : IDetailPresenter, ICallback<List<Boolean>> {

    override fun setBookMarked(view: View?, table: Table) {
        if ((table.isOccupied && table.customerID == -1)
                || (table.customerID != -1 && table.customerID != customerID)) {
            ninjaComponent?.util()?.logI("already booked, released by 15 mins")
            ninjaComponent?.util()?.toast(ninjaComponent.resources().getString(R.string.error_book))
            return
        }
        table.isOccupied = !table.isOccupied
        table.customerID = if (table.isOccupied) customerID else -1
        ninjaComponent?.database()?.insertTable(table)
        ninjaComponent?.util()?.logI("table toggled")
        iDetailActivity.refresh()
    }

    override fun getTables() {
        ninjaComponent?.database()?.allTable?.
                subscribeOn(Schedulers.io())?.
                observeOn(AndroidSchedulers.mainThread())?.
                subscribe { list: List<Table> ->
                    if (list.isEmpty()) {//call only once
                        ninjaComponent.network()?.getTables(this)
                        ninjaComponent.util()?.logI("web call for once" + list.toString())
                    } else {
                        iDetailActivity.setTables(ArrayList(list))
                        ninjaComponent.util().logI("local" + list.toString())
                    }
                }
    }

    override fun onSuccess(result: List<Boolean>?) {
        result?.let {
            val arr = ArrayList<Table>()
            for (b in result) {
                arr.add(Table(b, -1))
            }
            ninjaComponent?.database()?.insertAllTable(arr)
            iDetailActivity.setTables(arr)
        }
    }

    override fun onError(t: Throwable) {
        ninjaComponent?.database()?.allTable?.
                subscribeOn(Schedulers.io())?.
                observeOn(AndroidSchedulers.mainThread())?.
                subscribe { list: List<Table> ->
                    if (list.isEmpty()) {
                        ninjaComponent?.util()?.logI("no local cache")
                        iDetailActivity.setTables(null)
                    } else {
                        ninjaComponent?.util()?.logI("got local cache")
                        iDetailActivity.setTables(ArrayList(list))
                    }
                }
    }

}