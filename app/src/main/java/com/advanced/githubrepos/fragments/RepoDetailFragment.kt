package com.advanced.githubrepos.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedDispatcher
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.advanced.githubrepos.R
import com.advanced.githubrepos.databinding.FragmentListReposBinding
import com.advanced.githubrepos.databinding.FragmentRepoDetailBinding
import com.advanced.githubrepos.models.Item

class RepoDetailFragment() : Fragment() {

    lateinit var binding: FragmentRepoDetailBinding
    private val args: RepoDetailFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRepoDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.repoName.text = args.name
        binding.repoDes.text = args.des
        binding.repoStars.text = args.stars.toString()

    }
}