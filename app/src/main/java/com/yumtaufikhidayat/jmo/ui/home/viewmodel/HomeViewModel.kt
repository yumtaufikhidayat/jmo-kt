package com.yumtaufikhidayat.jmo.ui.home.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.yumtaufikhidayat.jmo.data.repository.JMORepository
import com.yumtaufikhidayat.jmo.model.auth.UserModel
import com.yumtaufikhidayat.jmo.model.home.ServiceProgram
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: JMORepository) : ViewModel() {
    fun getUser(): Flow<UserModel> = repository.getUser()

    fun listOfServiceProgram(context: Context): List<ServiceProgram> = repository.listOfServiceProgram(context)
}