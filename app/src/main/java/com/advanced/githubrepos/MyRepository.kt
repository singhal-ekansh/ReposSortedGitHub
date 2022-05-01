package com.advanced.githubrepos

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException

class MyRepository {
    private var allRepos: MutableLiveData<ArrayList<Repos>> = MutableLiveData()
    private var repos: ArrayList<Repos> = ArrayList()


    @OptIn(DelicateCoroutinesApi::class)
    fun callApi(): MutableLiveData<ArrayList<Repos>> {
        GlobalScope.launch {
            val response: Response<JsonObject> = RetrofitClient.api.getReposByStars()
            if (response.isSuccessful) {
                try {
                    Log.d("data", response.body().toString())
                    val jsonArray = response.body()?.get("items")?.asJsonArray

                    for (i in 0 until jsonArray!!.size()) {
                        val obj: JsonObject = jsonArray[i].asJsonObject
                        repos.add(
                            Repos(
                                obj.get("name").asString,
                                obj.get("owner").asJsonObject.get("avatar_url").asString,
                                obj.get("stargazers_count").asInt,
                                obj.get("description").asString
                            )
                        )
                    }
                } catch (e: Error) {

                    Log.d("livedata", e.message.toString())
                }

            }
            allRepos.postValue(repos)
        }


        return allRepos
    }


}