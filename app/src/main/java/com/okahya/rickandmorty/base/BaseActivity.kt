package com.okahya.rickandmorty.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<DB : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity() {

    @get:LayoutRes
    protected abstract val layoutResourceId: Int

    protected abstract val viewModel: VM

    private var _binding: DB? = null
    val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = DataBindingUtil.setContentView(this, layoutResourceId)
        initialize()
    }

    open fun initialize() {}

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}