package com.yumtaufikhidayat.jmo.ui.home.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.yumtaufikhidayat.jmo.data.repository.JMORepository
import com.yumtaufikhidayat.jmo.model.home.ImageSlider
import com.yumtaufikhidayat.jmo.model.home.ServiceProgram
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: JMORepository) : ViewModel() {

    fun listOfServiceProgram(context: Context): List<ServiceProgram> = repository.listOfServiceProgram(context)

    fun listOfImageSlider(): List<ImageSlider> = repository.listOfImageSlider()

    fun listOfOtherService(context: Context): List<ServiceProgram> = repository.listOfOtherService(context)
}