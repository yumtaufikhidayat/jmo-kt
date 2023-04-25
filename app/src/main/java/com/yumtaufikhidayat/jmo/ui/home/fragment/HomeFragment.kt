package com.yumtaufikhidayat.jmo.ui.home.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.yumtaufikhidayat.jmo.R
import com.yumtaufikhidayat.jmo.databinding.FragmentHomeBinding
import com.yumtaufikhidayat.jmo.ui.home.adapter.OtherServiceAdapter
import com.yumtaufikhidayat.jmo.ui.home.adapter.ServiceProgramAdapter
import com.yumtaufikhidayat.jmo.ui.home.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<HomeViewModel>()
    private val delayTime = 2000L
    private var doubleBackToExitPressedOnce = false
    private val serviceProgramAdapter by lazy { ServiceProgramAdapter() }
    private val otherServiceAdapter by lazy { OtherServiceAdapter() }

    private val backPressedCallback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (doubleBackToExitPressedOnce) {
                requireActivity().finish()
                return
            }

            doubleBackToExitPressedOnce = true
            Toast.makeText(requireContext(), "Ketuk sekali lagi untuk keluar ", Toast.LENGTH_SHORT).show()

            Handler(Looper.getMainLooper())
                .postDelayed({
                    doubleBackToExitPressedOnce = false
                }, delayTime)
        }
    }

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
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            backPressedCallback
        )

        initServiceProgramAdapter()
        initOtherProgramAdapter()
        setServiceProgramData()
        setOtherServiceProgramData()
        setServiceProgramListener()
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