package com.advanced.githubrepos

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Response

class MyRepository() {
    private var allRepos: MutableLiveData<ArrayList<Repos>> = MutableLiveData()
    private var repos: ArrayList<Repos> = ArrayList()


    fun callApi(): MutableLiveData<ArrayList<Repos>> {
        GlobalScope.launch {
            val response: Response<ResponseBody> = RetrofitClient.api.getReposByStars()
            if (response.isSuccessful) {
                val jsonArray = JSONArray(response.body().toString())
                for (i in 0 until jsonArray.length()) {
                    val obj: JSONObject = jsonArray.getJSONObject(i)
                    repos.add(
                        Repos(
                            obj.getString("name"),
                            obj.getJSONObject("owner").getString("avatar_url"),
                            obj.getInt("stargazers_count"),
                            obj.getString("description")
                        )
                    )
                }
                allRepos.value = repos
            }
        }

        return allRepos
    }

}