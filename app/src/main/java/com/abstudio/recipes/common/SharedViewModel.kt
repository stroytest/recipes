package com.abstudio.recipes.common

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.abstudio.recipes.data.entities.Category

class SharedViewModel(application: Application) : AndroidViewModel(application)  {

    val emptyDataBase: MutableLiveData<Boolean> = MutableLiveData(false)

    fun checkIfDataBaseEmpty(categories: List<Category>) {
        emptyDataBase.value = categories.isEmpty()
    }


}