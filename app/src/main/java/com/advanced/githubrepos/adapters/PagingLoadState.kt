package com.advanced.githubrepos.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.advanced.githubrepos.databinding.LoadErrorBinding


class PagingLoadState(private val retry: () -> Unit) :
    LoadStateAdapter<PagingLoadState.LoadingViewHolder>() {

    class LoadingViewHolder(private val binding: LoadErrorBinding, retry: () -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.retry.setOnClickListener {
                retry()
            }
        }

        fun bind(loadState: LoadState) {
            binding.apply {
                loadProgress.isVisible = loadState is LoadState.Loading
                retry.isVisible = loadState is LoadState.Error
            }
        }
    }

    override fun onBindViewHolder(holder: LoadingViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadingViewHolder {
        return LoadingViewHolder(
            LoadErrorBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), retry
        )
    }
}