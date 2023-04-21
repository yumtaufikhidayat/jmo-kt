package com.yumtaufikhidayat.jmo.ui.auth.register.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yumtaufikhidayat.jmo.data.repository.JMORepository
import com.yumtaufikhidayat.jmo.model.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: JMORepository
) : ViewModel() {
    fun registerUser(userModel: UserModel) = viewModelScope.launch {
        repository.registerUser(userModel)
    }
}