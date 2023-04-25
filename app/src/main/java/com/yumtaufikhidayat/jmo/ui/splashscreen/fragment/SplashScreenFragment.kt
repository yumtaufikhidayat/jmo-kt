package com.yumtaufikhidayat.jmo.ui.splashscreen.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.yumtaufikhidayat.jmo.R
import com.yumtaufikhidayat.jmo.databinding.FragmentSplashscreenBinding
import com.yumtaufikhidayat.jmo.ui.splashscreen.viewmodel.SplashScreenViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashScreenFragment : Fragment() {

    private var _binding: FragmentSplashscreenBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<SplashScreenViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashscreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkIsLogin()
    }

    private fun checkIsLogin() {
        lifecycleScope.launch {
            delay(2000)
            viewModel.getUser().collect {
                if (!it.isLogin) {
                    findNavController().apply {
                        popBackStack(R.id.splashScreenFragment, true)
                        navigate(R.id.loginFragment)
                    }
                } else {
                    findNavController().apply {
                        popBackStack(R.id.splashScreenFragment, true)
                        navigate(R.id.homeFragment)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}