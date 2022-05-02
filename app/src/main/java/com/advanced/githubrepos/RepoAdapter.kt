package com.advanced.githubrepos

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RepoAdapter(private val context: Context) : RecyclerView.Adapter<RepoAdapter.RepoViewHolder>() {

    var repoList: ArrayList<Repos> = ArrayList()

    class RepoViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val repoNameView = ItemView.findViewById<TextView>(R.id.repoNameView)!!
        val repoImageView = ItemView.findViewById<ImageView>(R.id.repoImageView)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.repo_card, parent, false)

        return RepoViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val itemViewModel = repoList[position]
        holder.repoNameView.text = itemViewModel.name
        Glide.with(context).load(itemViewModel.icon).into(holder.repoImageView)
    }

    override fun getItemCount(): Int {
        return repoList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateRepoList(repos: ArrayList<Repos>) {
        repoList.addAll(repos)
        this.notifyDataSetChanged()
    }
}