package com.yumtaufikhidayat.jmo.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.yumtaufikhidayat.jmo.R
import com.yumtaufikhidayat.jmo.databinding.ActivityMainBinding
import com.yumtaufikhidayat.jmo.ui.auth.login.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<LoginViewModel>()
    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setNavHost()
        setUpNavigationDestination()
    }

    private fun setNavHost() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentMainContainer) as NavHostFragment
        navController = navHostFragment.findNavController()
        navController?.let { binding.navBottom.setupWithNavController(it) }
    }

    private fun setUpNavigationDestination() {
        navController?.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment-> {
                    lifecycleScope.launch {
                        viewModel.getUser().collect {
                            if (it.isLogin) {
                                showHideBottomNavigation(true)
                            } else {
                                showHideBottomNavigation(false)
                            }
                        }
                    }
                }
                R.id.splashScreenFragment,
                R.id.loginFragment,
                R.id.registerFragment,
                R.id.homeDetailFragment,
                R.id.newsDetailFragment -> showHideBottomNavigation(false)
                else -> showHideBottomNavigation(true)
            }
        }
    }

    private fun showHideBottomNavigation(isShow: Boolean) {
        binding.navBottom.isVisible = isShow
    }

    override fun onDestroy() {
        super.onDestroy()
        navController = null
    }
}