package com.advanced.githubrepos.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.advanced.githubrepos.R
import com.advanced.githubrepos.adapters.RepoAdapter
import com.advanced.githubrepos.databinding.FragmentListReposBinding
import com.advanced.githubrepos.models.Item
import com.advanced.githubrepos.viewmodels.MyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListReposFragment(context: Context) : Fragment(), RepoAdapter.OnRepoClick {
    private val repoAdapter: RepoAdapter = RepoAdapter(context, this)
    private val viewModel: MyViewModel by viewModels()
    private lateinit var binding: FragmentListReposBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentListReposBinding.inflate(inflater, container, false)
        binding.RepoRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = repoAdapter
        }
        lifecycleScope.launch {
            viewModel.reposData.collect { pagingData ->
                repoAdapter.submitData(pagingData)
            }
        }
        return binding.root
    }

    override fun onCardClick(item: Item) {
        activity?.supportFragmentManager?.beginTransaction()?.hide(this)?.add(R.id.rootView, RepoDetailFragment(item))?.addToBackStack(null)?.commit()
    }

}