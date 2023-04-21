package com.yumtaufikhidayat.jmo.ui.profile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yumtaufikhidayat.jmo.data.repository.JMORepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: JMORepository
) : ViewModel() {
    fun logoutUser() = viewModelScope.launch {
        repository.logoutUser()
    }
}