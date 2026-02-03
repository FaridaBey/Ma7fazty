package com.example.ma7faza.presentation.auth.login

data class LoginState(
    val mobileNumber: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val loginsuccess: Boolean = false,
    val isPasswordVisible: Boolean = false,
    val signupSuccess: Boolean = false,
    val mobileError: String? = null,
    val passwordError: String? = null
)