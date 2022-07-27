package com.okahya.rickandmorty.scene.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.okahya.rickandmorty.R
import com.okahya.rickandmorty.scene.data.model.response.Character
import com.okahya.rickandmorty.base.ui.BaseFragment
import com.okahya.rickandmorty.databinding.FragmentHomeBinding
import com.okahya.rickandmorty.scene.ui.home.adapter.CharacterAdapter
import com.okahya.rickandmorty.scene.ui.home.adapter.FooterAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment: BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override val layoutResourceId: Int = R.layout.fragment_home
    override val viewModel: HomeViewModel by viewModels()
    private var characterAdapter = CharacterAdapter(object : CharacterAdapter.OnItemClickListener {
        override fun onItemClick(character: Character) {
            onItemClicked(character)
        }
    })

    var isStateChanged = false

    override fun initialize() {
        binding.viewModel = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        submitData()
    }

    private fun initRecyclerView() {
        characterAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY

        with(binding.recyclerViewCharacter) {
            layoutManager = getGridLayoutManager()
            adapter = characterAdapter.withLoadStateFooter(FooterAdapter(characterAdapter::retry))
        }
    }

    private fun getGridLayoutManager(): GridLayoutManager {
        val grid = GridLayoutManager(activity, 2)

        grid.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(number: Int) = when (number) {
                isCharacterItem() -> 2
                else -> 1
            }
        }

        return grid
    }

    private fun isCharacterItem() = (CharacterAdapter.CharacterAdapterState.CHARACTER.value - 1)

    private fun submitData() {
        checkStateForSubmitData {
            lifecycleScope.launch {
                collectData()
            }
        }
    }

    private suspend fun collectData() {
        viewModel.getCharacters().collectLatest { pagingData ->
            characterAdapter.submitData(pagingData)
        }
    }

    private fun checkStateForSubmitData(onCalled: () -> Unit) {
        if (!isStateChanged) {
            onCalled.invoke()
            isStateChanged = true
        }
    }

    fun onItemClicked(character: Character) {
        navigateDirections(HomeFragmentDirections.actionHomeFragmentToDetailFragment(character))
    }
}