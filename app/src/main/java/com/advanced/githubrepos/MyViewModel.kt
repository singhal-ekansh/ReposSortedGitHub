package com.advanced.githubrepos

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.DelicateCoroutinesApi

class MyViewModel(application: Application) : AndroidViewModel(application) {

    var repository: MyRepository = MyRepository()

    @DelicateCoroutinesApi
    fun loadData(): MutableLiveData<ArrayList<Repos>> {
        return repository.callApi()
    }
}