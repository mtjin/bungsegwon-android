package com.mtjin.bungsegwon.views.main

import androidx.activity.addCallback
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.mtjin.bungsegwon.R
import com.mtjin.bungsegwon.base.BaseFragment
import com.mtjin.bungsegwon.databinding.FragmentMainBinding
import com.mtjin.bungsegwon.utils.KeepStateNavigator

class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {
    private val viewModel: MainViewModel by activityViewModels()

    override fun init() {
        initNavigation()
        initBackPressed()
        initViewModelCallback()
    }

    private fun initViewModelCallback() {
        viewModel.navDirection.observe(this) {
            findNavController().navigate(it)
        }
    }

    private fun initBackPressed() {
        var lastPressedTime = 0L
        requireActivity().onBackPressedDispatcher.addCallback {
            val currentTime = System.currentTimeMillis()
            if (lastPressedTime + 2000 > currentTime) {
                requireActivity().finish()
            } else {
                lastPressedTime = currentTime
                showToast(resources.getString(R.string.finish_msg))
            }
        }
    }

    private fun initNavigation() {
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.main_navigation_view) as NavHostFragment
        val navController = navHostFragment.navController
        val keepStateNavigator = KeepStateNavigator(
            requireContext(),
            childFragmentManager,
            R.id.main_navigation_view
        )
        navController.navigatorProvider.addNavigator(keepStateNavigator)
        navController.setGraph(R.navigation.navigation_graph_main)
        binding.mainBottomNavigation.setupWithNavController(navController)
        binding.mainBottomNavigation.itemIconTintList = null
    }
}