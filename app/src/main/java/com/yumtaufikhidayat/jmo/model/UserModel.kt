package com.yumtaufikhidayat.jmo.model

data class UserModel(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val isLogin: Boolean = false
)
