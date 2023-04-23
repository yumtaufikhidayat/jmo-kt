package com.yumtaufikhidayat.jmo.data.source

import android.content.Context
import com.yumtaufikhidayat.jmo.data.local.DummyData
import com.yumtaufikhidayat.jmo.model.profile.Profile
import javax.inject.Inject

class LocalDataSource @Inject constructor() {
    fun listOfProfileMenu(context: Context): List<Profile> = DummyData.provideListOfProfileMenu(context)
}