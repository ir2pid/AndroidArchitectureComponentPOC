package com.noisyninja.poc.layers.database.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.noisyninja.poc.layers.database.DataBaseModule2

import com.noisyninja.poc.model.Table

/**
 * Created by sudiptadutta on 10/05/18.
 */

class TableViewModel(val dataBaseModule: DataBaseModule2) : ViewModel() {

    private var mutableLiveData: MutableLiveData<List<Table>> = MutableLiveData()


}
