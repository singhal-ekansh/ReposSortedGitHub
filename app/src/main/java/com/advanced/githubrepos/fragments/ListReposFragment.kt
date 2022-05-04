package com.advanced.githubrepos.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.advanced.githubrepos.MainActivity
import com.advanced.githubrepos.R
import com.advanced.githubrepos.adapters.RepoAdapter
import com.advanced.githubrepos.databinding.FragmentListReposBinding
import com.advanced.githubrepos.models.Item
import com.advanced.githubrepos.viewmodels.MyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListReposFragment() : Fragment(), RepoAdapter.OnRepoClick {
    private lateinit var repoAdapter: RepoAdapter
    private val viewModel: MyViewModel by viewModels()
    private lateinit var binding: FragmentListReposBinding
    var init = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        if(!init)
        {
            binding = FragmentListReposBinding.inflate(inflater, container, false)

            repoAdapter = RepoAdapter(requireContext(), this)

            binding.RepoRecycler.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = repoAdapter
            }

            lifecycleScope.launch {
                viewModel.reposData.collect { pagingData ->
                    repoAdapter.submitData(pagingData)
                }
            }
            init = true
        }

        return binding.root
    }


    override fun onCardClick(item: Item) {

        findNavController().navigate(
            ListReposFragmentDirections.actionListReposFragment3ToRepoDetailFragment3(
                item.name, item.description, item.stargazers_count
            )
        )
    }

}