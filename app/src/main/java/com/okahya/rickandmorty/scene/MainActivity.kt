package com.okahya.rickandmorty.scene

import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import dagger.hilt.android.AndroidEntryPoint
import com.okahya.rickandmorty.R
import com.okahya.rickandmorty.base.ui.BaseActivity
import com.okahya.rickandmorty.base.util.EventObserver
import com.okahya.rickandmorty.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val layoutResourceId: Int = R.layout.activity_main
    override val viewModel: MainViewModel by viewModels()
    private lateinit var navController: NavController

    override fun initialize() {
        super.initialize()

        setNavigationGraph()
        setUpObservableFields()
    }

    private fun setNavigationGraph() {
        val toolbar = binding.toolbarMain
        setSupportActionBar(toolbar)

        navController = Navigation.findNavController(this, R.id.fragmentContainerView)
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.homeFragment
        ))
        setupActionBarWithNavController(navController, appBarConfiguration)
        setNavigationBackIcon(toolbar)
    }

    private fun setNavigationBackIcon(toolbar: Toolbar) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id != R.id.homeFragment) {
                toolbar.setNavigationIcon(R.drawable.ic_cancel)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun setUpObservableFields() {
        viewModel.toolbar.observe(this, EventObserver {
            if (it) {
                showToolbar()
                setNavigationGraph()
            } else {
                hideToolbar()
            }
        })

        viewModel.loading.observe(this, EventObserver {
            if (it) { showLoading() } else { hideLoading() }
        })
    }

    private fun showToolbar() {
        binding.appbar.visibility = View.VISIBLE
        binding.toolbarMain.visibility = View.VISIBLE
    }

    private fun hideToolbar() {
        binding.appbar.visibility = View.INVISIBLE
        binding.toolbarMain.visibility = View.INVISIBLE
    }

    private fun showLoading() {
        binding.progressbarLoading.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.progressbarLoading.visibility = View.INVISIBLE
    }
}