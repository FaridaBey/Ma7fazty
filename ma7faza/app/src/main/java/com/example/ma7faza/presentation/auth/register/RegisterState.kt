package com.example.ma7faza.presentation.auth.register

data class RegisterState (
    val mobileNumber: String = "",
    val password: String = "",
    val name: String = "",
    val isLoading: Boolean = false,
    val createsuccess: Boolean = false,
    val backclick: Boolean = false,
    val isPasswordVisible: Boolean = false,
    val mobileError: String? = null,
    val passwordError: String? = null,
    val nameError: String? = null

)