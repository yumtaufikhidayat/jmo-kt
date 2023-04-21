package com.yumtaufikhidayat.jmo.data.repository

import com.yumtaufikhidayat.jmo.data.local.JMOPreference
import com.yumtaufikhidayat.jmo.model.UserModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class JMORepository @Inject constructor(
    private val jmoPreference: JMOPreference
) {
    fun getUser(): Flow<UserModel> = jmoPreference.getUser()

    suspend fun registerUser(userModel: UserModel) = jmoPreference.registerUser(userModel)

    suspend fun loginUser() = jmoPreference.loginUser()

    suspend fun logoutUser() = jmoPreference.logoutUser()
}