package com.yumtaufikhidayat.jmo.ui.auth.login.fragment

import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.yumtaufikhidayat.jmo.R
import com.yumtaufikhidayat.jmo.databinding.FragmentLoginBinding
import com.yumtaufikhidayat.jmo.model.auth.UserModel
import com.yumtaufikhidayat.jmo.ui.auth.login.viewmodel.LoginViewModel
import com.yumtaufikhidayat.jmo.utils.Common
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<LoginViewModel>()
    private var userModel: UserModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createAccount()
        getUser()
        loginUser()
    }

    private fun createAccount() {
        binding.tvCreateAccount.setOnClickListener {
            navigateToRegister()
        }
    }

    private fun getUser() {
        lifecycleScope.launch {
            viewModel.getUser().collect {
                userModel = it
            }
        }
    }

    private fun loginUser() {
        binding.apply {
            btnLogin.setOnClickListener {
                val email = etEmail.text.toString().trim()
                val password = etPassword.text.toString().trim()

                when {
                    email != userModel?.email -> showDialog(getString(R.string.txt_login_failed_email))
                    password != userModel?.password -> showDialog(getString(R.string.txt_login_failed_password))
                    else -> {
                        lifecycleScope.launch {
                            viewModel.loginUser()
                            showLoading(true)
                            delay(Common.DELAY_TIME)
                            showDialog(getString(R.string.txt_login_success))
                            showLoading(false)
                        }
                    }
                }
            }
            etEmail.addTextChangedListener(textWatcher())
            etPassword.addTextChangedListener(textWatcher())
            cbShowPassword.setOnCheckedChangeListener { _, isChecked ->
                etPassword.inputType = if (isChecked) {
                    InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                } else {
                    InputType.TYPE_TEXT_VARIATION_PASSWORD or InputType.TYPE_CLASS_TEXT
                }
            }
        }
    }

    private fun textWatcher(): TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            setEnabledButton()
        }

        override fun afterTextChanged(p0: Editable?) {}
    }

    private fun setEnabledButton() {
        binding.apply {
            val email = etEmail.text
            val password = etPassword.text
            btnLogin.isEnabled = (email != null && email.toString().isNotEmpty())
                    && (password != null && password.toString().isNotEmpty()
                    && password.toString().length >= 6)
        }
    }

    private fun showDialog(text: String) {
        MaterialAlertDialogBuilder(requireContext()).apply {
            setTitle(resources.getString(R.string.txt_login))
            setMessage(text)
            setCancelable(false)

            when (text) {
                resources.getString(R.string.txt_login_failed_email),
                resources.getString(R.string.txt_login_failed_password) -> {
                    setPositiveButton(resources.getString(R.string.txt_close)) { _, _ ->
                        show().dismiss()
                    }
                }
                else -> {
                    setPositiveButton(resources.getString(R.string.txt_oke)) { _, _ ->
                        findNavController().apply {
                            popBackStack(R.id.loginFragment, true)
                            navigate(R.id.homeFragment)
                        }
                    }
                }
            }
            show()
        }
    }

    private fun showLoading(isShow: Boolean) {
        binding.pbLoading.visibility = if (isShow) View.VISIBLE else View.GONE
    }

    private fun navigateToRegister() {
        findNavController().apply {
            popBackStack(R.id.loginFragment, true)
            navigate(R.id.registerFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}