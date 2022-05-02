package com.advanced.githubrepos

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel()
class MyViewModel
@Inject constructor(private val api: Api) : ViewModel() {

    val reposData = Pager(PagingConfig(pageSize = 1)) {
        MyRepository(api)
    }.flow.cachedIn(viewModelScope)

}