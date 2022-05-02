package com.advanced.githubrepos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.DelicateCoroutinesApi

class MainActivity : AppCompatActivity() {

    private val adapter: RepoAdapter = RepoAdapter(this)

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recycler = findViewById<RecyclerView>(R.id.RepoRecycler)
        recycler.layoutManager = LinearLayoutManager(this)

        recycler.adapter = adapter
        MyViewModel(application).loadData().observe(this, Observer {
            Log.d("livedata", it.toString())
            adapter.updateRepoList(it)
        })

    }
}