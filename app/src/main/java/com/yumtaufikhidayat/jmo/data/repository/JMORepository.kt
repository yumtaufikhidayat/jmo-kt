package com.yumtaufikhidayat.jmo.data.repository

import android.content.Context
import com.yumtaufikhidayat.jmo.data.local.JMOPreference
import com.yumtaufikhidayat.jmo.data.source.LocalDataSource
import com.yumtaufikhidayat.jmo.model.auth.UserModel
import com.yumtaufikhidayat.jmo.model.profile.Profile
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class JMORepository @Inject constructor(
    private val jmoPreference: JMOPreference,
    private val localDataSource: LocalDataSource
) {
    fun getUser(): Flow<UserModel> = jmoPreference.getUser()

    suspend fun registerUser(userModel: UserModel) = jmoPreference.registerUser(userModel)

    suspend fun loginUser() = jmoPreference.loginUser()

    suspend fun logoutUser() = jmoPreference.logoutUser()

    fun listOfProfileMenu(context: Context): List<Profile> = localDataSource.listOfProfileMenu(context)
}