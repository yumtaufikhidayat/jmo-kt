package com.yumtaufikhidayat.jmo.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import com.yumtaufikhidayat.jmo.data.repository.JMORepository
import com.yumtaufikhidayat.jmo.model.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: JMORepository) : ViewModel() {
    fun getUser(): Flow<UserModel> = repository.getUser()
}