package com.example.ma7faza.presentation.auth.register

sealed class RegisterIntent {
     object CreateAccountClicked : RegisterIntent ()
     object BackClicked : RegisterIntent ()
     data class NameChanged(val name: String) : RegisterIntent ()
    data class MobileNumberChanged(val mobileNumber: String) : RegisterIntent ()
    data class PasswordChanged(val password: String) : RegisterIntent ()
    object TogglePasswordVisibility : RegisterIntent ()

}
