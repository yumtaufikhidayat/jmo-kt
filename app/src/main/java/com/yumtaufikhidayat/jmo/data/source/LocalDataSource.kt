package com.yumtaufikhidayat.jmo.data.source

import android.content.Context
import com.yumtaufikhidayat.jmo.data.local.DummyData
import com.yumtaufikhidayat.jmo.model.home.ImageSlider
import com.yumtaufikhidayat.jmo.model.home.ServiceProgram
import com.yumtaufikhidayat.jmo.model.profile.Profile
import javax.inject.Inject

class LocalDataSource @Inject constructor() {
    fun listOfServiceProgram(context: Context): List<ServiceProgram> = DummyData.provideListOfServiceProgram(context)

    fun listOfOtherServices(context: Context): List<ServiceProgram> = DummyData.provideListOfOtherServices(context)

    fun listOfImageSlider(): List<ImageSlider> = DummyData.provideImageSlider()

    fun listOfProfileMenu(context: Context): List<Profile> = DummyData.provideListOfProfileMenu(context)
}