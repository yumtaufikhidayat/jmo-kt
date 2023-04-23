package com.yumtaufikhidayat.jmo.ui.auth.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yumtaufikhidayat.jmo.data.repository.JMORepository
import com.yumtaufikhidayat.jmo.model.auth.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: JMORepository
): ViewModel() {
    fun getUser(): Flow<UserModel> = repository.getUser()
    fun loginUser() = viewModelScope.launch {
        repository.loginUser()
    }
}