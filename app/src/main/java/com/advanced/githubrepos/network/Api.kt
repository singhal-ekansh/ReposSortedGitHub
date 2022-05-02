package com.advanced.githubrepos.network

import com.advanced.githubrepos.models.ResponseModel
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("/search/repositories?q=created&sort=stars&per_page=25")
    suspend fun getReposByStars(@Query("page") page :Int) : Response<ResponseModel>
}