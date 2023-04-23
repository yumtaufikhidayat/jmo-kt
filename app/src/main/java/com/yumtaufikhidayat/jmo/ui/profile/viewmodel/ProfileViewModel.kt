package com.yumtaufikhidayat.jmo.ui.profile.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yumtaufikhidayat.jmo.data.repository.JMORepository
import com.yumtaufikhidayat.jmo.model.profile.Profile
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

    fun listOfProfileMenu(context: Context): List<Profile> = repository.listOfProfileMenu(context)
}