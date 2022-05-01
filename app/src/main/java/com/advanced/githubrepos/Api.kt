package com.advanced.githubrepos

import com.google.gson.JsonObject
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("/search/repositories?q=created&sort=stars&order=desc")
    suspend fun getReposByStars() : Response<ResponseBody>
}