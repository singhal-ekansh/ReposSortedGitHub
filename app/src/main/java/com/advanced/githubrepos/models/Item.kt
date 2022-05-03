package com.advanced.githubrepos.models

import android.os.Parcel
import android.os.Parcelable

data class Item(

    val description: String,

    val id: Int,

    val name: String,

    val owner: Owner,

    val stargazers_count: Int,


    )