package com.okahya.rickandmorty.scene.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.okahya.rickandmorty.R
import com.okahya.rickandmorty.base.ui.BaseFragment
import com.okahya.rickandmorty.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(){

    override val layoutResourceId: Int = R.layout.fragment_home
    override val viewModel: HomeViewModel by viewModels()

    override fun initialize() {
        binding.viewModel = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}