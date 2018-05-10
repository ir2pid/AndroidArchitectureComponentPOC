package com.noisyninja.poc.view.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.annotation.Nullable
import android.support.v7.app.AppCompatActivity
import com.noisyninja.poc.layers.database.viewmodel.CustomerViewModel
import com.noisyninja.poc.layers.di.NinjaComponent
import com.noisyninja.poc.layers.di.NinjaInjector.ninjaApplication
import com.noisyninja.poc.layers.network.ICallback
import com.noisyninja.poc.model.Customer
import com.noisyninja.poc.view.detail.DetailActivity
import com.noisyninja.poc.view.interfaces.IMainActivity
import com.noisyninja.poc.view.interfaces.IMainPresenter


/**
 * MainPresenter
 * Created by sudiptadutta on 28/04/18.
 */

class MainPresenter internal constructor(private val iMainActivity: IMainActivity, private val ninjaComponent: NinjaComponent?) : IMainPresenter, ICallback<List<Customer>> {

    override fun getCustomers() {
        ninjaComponent?.network()?.getCustomers(this)
    }

    override fun openDetail(id: Int) {
        val intent = Intent(ninjaApplication?.applicationContext, DetailActivity::class.java)
        intent.putExtra("customerID", id)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        ninjaApplication?.applicationContext?.startActivity(intent)
    }

    override fun onSuccess(result: List<Customer>?) {
        if (result == null) {
            //ninjaComponent.util().logI(MainPresenter::class.java, "null response")
            iMainActivity.setCustomers(null)
        } else {
            //ninjaComponent.util().logI(MainPresenter::class.java, "got response")
            iMainActivity.setCustomers(ArrayList(result))
            ninjaComponent?.database2()?.insertAll(result)
        }
    }

    override fun onError(t: Throwable) {

        val customerViewModel: CustomerViewModel = ViewModelProviders.of(iMainActivity as AppCompatActivity, ninjaComponent?.
                vmf()).get(CustomerViewModel::class.java)

        customerViewModel.getCustomers().
                observe(iMainActivity as AppCompatActivity, object : Observer<List<Customer>> {
                    override fun onChanged(@Nullable customerlist: List<Customer>?) {
                        // customerViewModel.getCustomers().removeObserver(this)//to not update
                        if (customerlist == null || customerlist.isEmpty()) {
                            ninjaComponent?.util()?.logI("no local cache")
                            iMainActivity.setCustomers(null)
                        } else {
                            ninjaComponent?.util()?.logI("got local cache")
                            iMainActivity.setCustomers(ArrayList(customerlist))
                        }
                    }

                })
        /*ninjaComponent?.database()?.all?.
                subscribeOn(Schedulers.io())?.
                observeOn(AndroidSchedulers.mainThread())?.
                subscribe { list: List<Customer> ->
                    if (list.isEmpty()) {
                        //ninjaComponent.util().logI(MainPresenter::class.java, "no local cache")
                        iMainActivity.setCustomers(null)
                    } else {
                        //ninjaComponent.util().logI(MainPresenter::class.java, "got local cache")
                        iMainActivity.setCustomers(ArrayList(list))
                    }
                }*/
    }

}
