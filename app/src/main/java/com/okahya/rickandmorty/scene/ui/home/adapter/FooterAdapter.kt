package com.okahya.rickandmorty.scene.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil.inflate
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.okahya.rickandmorty.R
import com.okahya.rickandmorty.databinding.ItemFooterBinding

class FooterAdapter(
    private val retry: () -> Unit
): LoadStateAdapter<FooterAdapter.FooterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): FooterViewHolder {
        val itemPagingFooterBinding = inflate<ItemFooterBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_footer,
            parent,
            false
        )

        return FooterViewHolder(itemPagingFooterBinding, retry)
    }

    override fun onBindViewHolder(holder: FooterViewHolder, loadState: LoadState) {
        with(holder) {
            setVisibilityState(loadState)
            checkErrorState(loadState)
        }
    }

    inner class FooterViewHolder(
        private val binding: ItemFooterBinding,
        retry: () -> Unit
    ): RecyclerView.ViewHolder(binding.root) {

        init {
            binding.buttonFooterRetry.setOnClickListener { retry.invoke() }
        }

        fun setVisibilityState(loadState: LoadState) {
            with(binding) {
                buttonFooterRetry.isVisible = loadState !is LoadState.Loading
                textViewFooterInformation.isVisible = loadState !is LoadState.Loading
                progressbarFooter.isVisible = loadState is LoadState.Loading
            }
        }

        fun checkErrorState(loadState: LoadState) {
            if (loadState is LoadState.Error){
                binding.textViewFooterInformation.text = loadState.error.localizedMessage
            }
        }
    }
}