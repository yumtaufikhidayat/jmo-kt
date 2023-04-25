package com.yumtaufikhidayat.jmo.ui.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.yumtaufikhidayat.jmo.R
import com.yumtaufikhidayat.jmo.databinding.FragmentHomeBinding
import com.yumtaufikhidayat.jmo.ui.home.adapter.OtherServiceAdapter
import com.yumtaufikhidayat.jmo.ui.home.adapter.ServiceProgramAdapter
import com.yumtaufikhidayat.jmo.ui.home.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<HomeViewModel>()
    private val serviceProgramAdapter by lazy { ServiceProgramAdapter() }
    private val otherServiceAdapter by lazy { OtherServiceAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkIsLogin()
        initServiceProgramAdapter()
        initOtherProgramAdapter()
        setServiceProgramData()
        setOtherServiceProgramData()
        setServiceProgramListener()
    }

    private fun checkIsLogin() {
        lifecycleScope.launch {
            viewModel.getUser().collect {
                if (!it.isLogin) {
                    findNavController().apply {
                        popBackStack(R.id.homeFragment, true)
                        navigate(R.id.loginFragment)
                    }
                }
            }
        }
    }

    private fun initServiceProgramAdapter() {
        binding.rvServiceProgram.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = serviceProgramAdapter
        }
    }

    private fun initOtherProgramAdapter() {
        binding.rvOtherService.apply {
            layoutManager = GridLayoutManager(requireContext(), 4)
            setHasFixedSize(true)
            adapter = otherServiceAdapter
        }
    }

    private fun setServiceProgramData() {
        val data = viewModel.listOfServiceProgram(requireContext())
        serviceProgramAdapter.submitList(data.take(2))
    }

    private fun setOtherServiceProgramData() {
        otherServiceAdapter.submitList(viewModel.listOfOtherService(requireContext()))
    }

    private fun setServiceProgramListener() {
        binding.btnOtherPrograms.setOnClickListener {
            findNavController().navigate(R.id.homeDetailFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}