package com.advanced.githubrepos.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.advanced.githubrepos.R
import com.advanced.githubrepos.databinding.FragmentListReposBinding
import com.advanced.githubrepos.databinding.FragmentRepoDetailBinding
import com.advanced.githubrepos.models.Item

class RepoDetailFragment(private val item: Item) : Fragment() {

    lateinit var binding: FragmentRepoDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRepoDetailBinding.inflate(inflater, container, false)

        binding.repoName.text = item.name
        binding.repoDes.text = item.description
        binding.repoStars.text = item.stargazers_count.toString()
        return binding.root
    }

}