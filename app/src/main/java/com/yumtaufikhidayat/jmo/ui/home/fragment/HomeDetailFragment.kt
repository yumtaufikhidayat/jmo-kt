package com.yumtaufikhidayat.jmo.ui.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.yumtaufikhidayat.jmo.R
import com.yumtaufikhidayat.jmo.databinding.FragmentHomeDetailBinding
import com.yumtaufikhidayat.jmo.ui.home.adapter.ServiceProgramAdapter
import com.yumtaufikhidayat.jmo.ui.home.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeDetailFragment : Fragment() {

    private var _binding: FragmentHomeDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<HomeViewModel>()
    private val serviceProgramAdapter by lazy { ServiceProgramAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleToolbar()
        initAdapter()
        setData()
    }

    private fun handleToolbar() {
        binding.toolbarHomeDetail.apply {
            tvToolbar.text = getString(R.string.txt_bpjamsostek_program)
            imgBack.apply {
                isVisible = true
                setOnClickListener {
                    findNavController().navigate(R.id.homeFragment)
                }
            }
        }
    }

    private fun initAdapter() {
        binding.rvServiceProgramDetail.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = serviceProgramAdapter
        }
    }

    private fun setData() {
        serviceProgramAdapter.submitList(viewModel.listOfServiceProgram(requireContext()))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}