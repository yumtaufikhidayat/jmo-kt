package com.yumtaufikhidayat.jmo.ui.splashscreen.viewmodel

import androidx.lifecycle.ViewModel
import com.yumtaufikhidayat.jmo.data.repository.JMORepository
import com.yumtaufikhidayat.jmo.model.auth.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(private val repository: JMORepository) : ViewModel(){
    fun getUser(): Flow<UserModel> = repository.getUser()
}