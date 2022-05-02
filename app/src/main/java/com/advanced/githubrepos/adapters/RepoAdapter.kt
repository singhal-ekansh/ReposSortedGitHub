package com.advanced.githubrepos.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.advanced.githubrepos.databinding.RepoCardBinding
import com.advanced.githubrepos.models.Item
import com.bumptech.glide.Glide

class RepoAdapter(private val context: Context) :
    PagingDataAdapter<Item, RepoAdapter.RepoViewHolder>(diffCallback) {

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem == newItem
            }

        }
    }

    inner class RepoViewHolder(val binding: RepoCardBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.binding.apply {
            repoNameView.text = currentItem?.name
            Glide.with(context).load(currentItem?.owner?.avatar_url).into(repoImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        return RepoViewHolder(
            RepoCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}