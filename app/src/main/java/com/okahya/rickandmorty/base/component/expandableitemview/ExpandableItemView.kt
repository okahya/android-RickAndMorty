package com.okahya.rickandmorty.base.component.expandableitemview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.okahya.rickandmorty.R
import com.okahya.rickandmorty.base.component.BaseLayout
import com.okahya.rickandmorty.databinding.*

class ExpandableItemView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : BaseLayout<ComponentExpandableItemViewBinding?>(context, attributeSet, defStyleAttr) {

    private lateinit var adapter: Adapter
    private val items: MutableList<String> = mutableListOf()
    private var isCollapse = false

    override fun onInitialized(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) {
        initViews()

        binding?.run {
            imageViewExpandMore.setOnClickListener { checkCollapseState() }
        }
    }

    override fun getLayoutResId(): Int {
        return R.layout.component_expandable_item_view
    }

    private fun checkCollapseState() {
        if (isCollapse) {
            collapse()
        } else {
            expand()
        }
    }

    private fun collapse() {
        binding?.recyclerViewEpisode?.visibility = View.GONE
        setImageResource(binding?.imageViewExpandMore, R.drawable.ic_expand_more)
        isCollapse = false
    }

    private fun expand() {
        binding?.recyclerViewEpisode?.visibility = View.VISIBLE
        setImageResource(binding?.imageViewExpandMore, R.drawable.ic_expand_less)
        isCollapse = true
    }

    fun getRecyclerView(): RecyclerView? {
        return binding?.recyclerViewEpisode
    }

    private fun initViews() {
        adapter = Adapter()
        binding?.recyclerViewEpisode?.adapter = adapter
    }

    fun setItems(items: List<String>?) {
        this.items.clear()
        items?.let {
            this.items.addAll(it)
            adapter.setData(it)
        }
    }

    class Adapter: RecyclerView.Adapter<Adapter.ViewHolder>() {
        private val data = mutableListOf<String>()

        fun setData(data: List<String>) {
            this.data.clear()
            this.data.addAll(data)
            notifyDataSetChanged()
        }

        override fun getItemCount(): Int {
            return data.size
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val itemBinding = DataBindingUtil.inflate<ItemExpandableItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_expandable_item,
                parent,
                false
            )

            return ViewHolder(itemBinding)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(data[position])
        }

        inner class ViewHolder(private val binding: ItemExpandableItemBinding) : RecyclerView.ViewHolder(binding.root) {

            fun bind(text: String) {
                binding.textViewEpisodeName.text = text
            }
        }
    }
}