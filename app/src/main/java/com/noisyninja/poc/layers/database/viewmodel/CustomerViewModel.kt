package com.noisyninja.poc.layers.database.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.noisyninja.poc.layers.Utils
import com.noisyninja.poc.layers.database.DataBaseModule2
import com.noisyninja.poc.model.Customer


/**
 * Created by sudiptadutta on 10/05/18.
 */

class CustomerViewModel(var dataBaseModule: DataBaseModule2) : ViewModel() {

    private val customerLiveData: LiveData<List<Customer>>

    init {
        Utils.logI(this.javaClass, "CustomerViewModel")
        customerLiveData = dataBaseModule.all
    }

    fun getCustomers(): LiveData<List<Customer>> {
        return customerLiveData
    }
}
