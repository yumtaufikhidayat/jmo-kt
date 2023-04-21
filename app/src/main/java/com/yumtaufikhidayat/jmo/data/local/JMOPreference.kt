package com.yumtaufikhidayat.jmo.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.yumtaufikhidayat.jmo.model.UserModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class JMOPreference @Inject constructor(private val dataStore: DataStore<Preferences>) {
    fun getUser(): Flow<UserModel> {
        return dataStore.data.map { preferences ->
            UserModel(
                name = preferences[NAME_KEY].orEmpty(),
                email = preferences[EMAIL_KEY].orEmpty(),
                password = preferences[PASSWORD_KEY].orEmpty(),
                isLogin = preferences[STATE_KEY] ?: false
            )
        }
    }

    suspend fun loginUser() {
        dataStore.edit { preferences ->
            preferences[STATE_KEY] = true
        }
    }

    suspend fun logoutUser() {
        dataStore.edit { preferences ->
            preferences.apply {
                remove(NAME_KEY)
                remove(EMAIL_KEY)
                remove(PASSWORD_KEY)
                remove(STATE_KEY)
            }
            preferences[STATE_KEY] = false
        }
    }

    suspend fun registerUser(userModel: UserModel) {
        dataStore.edit { preferences ->
            preferences[NAME_KEY] = userModel.name
            preferences[EMAIL_KEY] = userModel.email
            preferences[PASSWORD_KEY] = userModel.password
            preferences[STATE_KEY] = userModel.isLogin
        }
    }

    companion object {
        private val NAME_KEY = stringPreferencesKey("name")
        private val EMAIL_KEY = stringPreferencesKey("email")
        private val PASSWORD_KEY = stringPreferencesKey("password")
        private val STATE_KEY = booleanPreferencesKey("state")
    }
}