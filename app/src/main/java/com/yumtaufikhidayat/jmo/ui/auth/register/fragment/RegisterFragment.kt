package com.yumtaufikhidayat.jmo.ui.auth.register.fragment

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
import com.yumtaufikhidayat.jmo.databinding.FragmentRegisterBinding
import com.yumtaufikhidayat.jmo.model.UserModel
import com.yumtaufikhidayat.jmo.ui.auth.register.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<RegisterViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginAccount()
        registerUser()
    }

    private fun registerUser() {
        binding.apply {
            btnRegister.setOnClickListener {
                val name = etName.text.toString()
                val email = etEmail.text.toString().trim()
                val password = etPassword.text.toString().trim()
                val userModel = UserModel(
                    name = name,
                    email = email,
                    password = password,
                    isLogin = false
                )

                lifecycleScope.launch {
                    viewModel.registerUser(userModel)
                    showLoading(true)
                    delay(5000)
                    showDialog(getString(R.string.text_register_success))
                    showLoading(false)
                }
            }
            etName.addTextChangedListener(textWatcher())
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

    private fun loginAccount() {
        binding.tvHaveAccount.setOnClickListener {
            navigateToLogin()
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
            val name = etName.text
            val email = etEmail.text
            val password = etPassword.text
            btnRegister.isEnabled = (name != null && name.toString().isNotEmpty())
                    && (email != null && email.toString().isNotEmpty())
                    && (password != null && password.toString().isNotEmpty()
                    && password.toString().length >= 6)
        }
    }

    private fun showDialog(text: String) {
        MaterialAlertDialogBuilder(requireContext()).apply {
            setTitle(resources.getString(R.string.txt_register))
            setMessage(text)
            setCancelable(false)

            when (text) {
                resources.getString(R.string.text_register_success) -> {
                    setPositiveButton(resources.getString(R.string.txt_login)) { _, _ ->
                        navigateToLogin()
                    }
                }
                else -> {
                    setPositiveButton(resources.getString(R.string.txt_close)) { _, _ ->
                        show().dismiss()
                    }
                }
            }
            show()
        }
    }

    private fun showLoading(isShow: Boolean) {
        binding.pbLoading.visibility = if (isShow) View.VISIBLE else View.GONE
    }

    private fun navigateToLogin() {
        findNavController().apply {
            popBackStack(R.id.registerFragment, true)
            navigate(R.id.loginFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}