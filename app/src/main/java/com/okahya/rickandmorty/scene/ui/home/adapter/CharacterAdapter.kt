package com.okahya.rickandmorty.scene.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.okahya.rickandmorty.R
import com.okahya.rickandmorty.base.network.remote.model.Character
import com.okahya.rickandmorty.databinding.ItemCharacterBinding
import com.okahya.rickandmorty.databinding.ItemHeaderBinding

class CharacterAdapter(private val listener: OnItemClickListener)
    : PagingDataAdapter<Character, RecyclerView.ViewHolder>(Comparator) {

    override fun getItemViewType(position: Int): Int = when (position) {
        CharacterAdapterState.HEADER.value -> R.layout.item_header
        else -> R.layout.item_character
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            R.layout.item_header -> {
                val binding = inflate<ItemHeaderBinding>(
                    LayoutInflater.from(parent.context),
                    viewType,
                    parent,
                    false
                )
                HeaderViewHolder(binding)
            }
            R.layout.item_character -> {
                val binding = inflate<ItemCharacterBinding>(
                    LayoutInflater.from(parent.context),
                    viewType,
                    parent,
                    false
                )
                CharacterViewHolder(binding)
            }
            else -> throw IllegalArgumentException("unknown view type $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> {
                holder.bind()
            }
            is CharacterViewHolder -> {
                getItem(position)?.let { holder.bind(it) }
            }
        }
    }

    object Comparator : DiffUtil.ItemCallback<Character>() {

        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }
    }

    inner class CharacterViewHolder(private val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(characterItem: Character) {
            with(binding.itemCharacter) {
                setCharacterName(characterItem.name)
                setCharacterImage(characterItem.image)

                clickListener = {
                    onItemClicked()
                }
            }
        }

        fun onItemClicked() {
            val position = bindingAdapterPosition

            if (position != RecyclerView.NO_POSITION) {
                val item = getItem(position)

                if (item != null) {
                    listener.onItemClick(item)
                }
            }
        }
    }

    inner class HeaderViewHolder(private val binding: ItemHeaderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.textViewHeader.text = "Characters"
        }
    }

    interface OnItemClickListener {
        fun onItemClick(character: Character)
    }

    enum class CharacterAdapterState(val value: Int) {
        HEADER(0), CHARACTER(1)
    }
}