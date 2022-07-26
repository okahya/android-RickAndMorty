package com.okahya.rickandmorty.scene.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.okahya.rickandmorty.R
import com.okahya.rickandmorty.base.ui.BaseFragment
import com.okahya.rickandmorty.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>(){

    override val layoutResourceId: Int = R.layout.fragment_detail
    override val viewModel: DetailViewModel by viewModels()

    override fun initialize() {
        super.initialize()
        binding.viewModel = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}