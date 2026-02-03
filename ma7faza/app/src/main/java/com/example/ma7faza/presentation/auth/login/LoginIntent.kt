package com.example.ma7faza.presentation.auth.login

sealed class LoginIntent {
    object LoginClicked : LoginIntent()
    object SignUpClicked : LoginIntent()
    data class MobileNumberChanged(val mobileNumber: String) : LoginIntent()
    data class PasswordChanged(val password: String) : LoginIntent()
    object TogglePasswordVisibility : LoginIntent()
}