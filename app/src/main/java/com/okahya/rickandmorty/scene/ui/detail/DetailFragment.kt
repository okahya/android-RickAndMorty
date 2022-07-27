package com.okahya.rickandmorty.scene.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.okahya.rickandmorty.R
import com.okahya.rickandmorty.base.ui.BaseFragment
import com.okahya.rickandmorty.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>(){

    override val layoutResourceId: Int = R.layout.fragment_detail
    override val viewModel: DetailViewModel by viewModels()

    private val argument: DetailFragmentArgs by navArgs()

    override fun initialize() {
        super.initialize()
        binding.viewModel = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setCharacterInfoText()
    }

    private fun characterInfo() = argument.characterInfo

    private fun setCharacterInfoText() {
        with(binding.characterDetailView) {
            characterInfo()?.let {
                setCharacterName(it.name)
                setCharacterStatusSpecies(it.status, it.species)
                setCharacterGender(it.gender)
                setCharacterImage(it.image)
            }
        }
    }
}