package com.noisyninja.poc.layers.database.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.noisyninja.poc.layers.database.DataBaseModule2
import javax.inject.Inject

/**
 * Created by sudiptadutta on 10/05/18.
 */

class ViewModelFactory @Inject constructor(val dataBaseModule: DataBaseModule2) : ViewModelProvider.NewInstanceFactory() {
    //val tableViewModel: TableViewModel, val customerViewModel: CustomerViewModel
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(TableViewModel::class.java)) {
            TableViewModel(dataBaseModule) as T
        } else {//(modelClass.equals(CustomerViewModel.class))
            CustomerViewModel(dataBaseModule) as T
        }
    }
}
