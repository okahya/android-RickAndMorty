package com.okahya.rickandmorty.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController

abstract class BaseFragment<DB : ViewDataBinding, VM : BaseViewModel> : Fragment() {

    @get:LayoutRes
    protected abstract val layoutResourceId: Int

    protected abstract val viewModel: VM

    private var _binding: DB? = null
    val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = DataBindingUtil.inflate(inflater, layoutResourceId, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        initialize()

        return binding.root
    }

    open fun initialize() {}

    fun navigateDirections(destination: NavDirections, popUpTo: Int? = null) = with(findNavController()) {
        currentDestination?.getAction(destination.actionId)?.let {
            navigate(destination, popUpTo?.let { NavOptions.Builder().setPopUpTo(it, true).build() } )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}