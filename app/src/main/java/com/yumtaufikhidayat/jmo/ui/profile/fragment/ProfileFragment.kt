package com.yumtaufikhidayat.jmo.ui.profile.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.yumtaufikhidayat.jmo.R
import com.yumtaufikhidayat.jmo.databinding.FragmentProfileBinding
import com.yumtaufikhidayat.jmo.ui.profile.adapter.ProfileAdapter
import com.yumtaufikhidayat.jmo.ui.profile.viewmodel.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<ProfileViewModel>()
    private var profileAdapter: ProfileAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbar()
        setProfileData()
        setAdapter()
    }

    private fun setToolbar() {
        binding.toolbarProfile.tvToolbar.text = getString(R.string.txt_profile)
    }

    private fun setProfileData() {
        profileAdapter = ProfileAdapter { position ->
            when (position) {
                5 -> logout()
            }
        }
        profileAdapter?.submitList(viewModel.listOfProfileMenu(requireContext()))
    }

    private fun logout() {
        MaterialAlertDialogBuilder(requireContext()).apply {
            setTitle(resources.getString(R.string.txt_logout))
            setMessage(resources.getString(R.string.txt_logout_desc))
            setCancelable(false)
            setNegativeButton(resources.getString(R.string.txt_cancel)) { _, _ ->
                show().dismiss()
            }
            setPositiveButton(resources.getString(R.string.txt_yes)) { _, _ ->
                viewModel.logoutUser()
                findNavController().apply {
                    popBackStack(R.id.profileFragment, true)
                    navigate(R.id.loginFragment)
                }
            }
            show()
        }
    }

    private fun setAdapter() {
        binding.rvProfileMenu.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = profileAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        profileAdapter = null
    }
}