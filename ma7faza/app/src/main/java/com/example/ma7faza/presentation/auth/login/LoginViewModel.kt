package com.example.ma7faza.presentation.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay

class loginViewModel : ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state.asStateFlow()

    //     checks for an intent
    fun processIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.MobileNumberChanged -> updateMobile(intent.mobileNumber)
            is LoginIntent.PasswordChanged -> updatePassword(intent.password)
            is LoginIntent.TogglePasswordVisibility -> togglePassword()
            is LoginIntent.LoginClicked -> login()
            is LoginIntent.SignUpClicked -> signup()
        }
    }

    private fun validateLogin(): Boolean {
        val mobileError = if (_state.value.mobileNumber.isEmpty()) "Mobile number required" else null
        val passwordError = if (_state.value.password.isEmpty()) "Password required" else null

        _state.value = _state.value.copy(
            mobileError = mobileError,
            passwordError = passwordError
        )
        return mobileError == null && passwordError == null
    }

    private fun updateMobile(mobileNumber: String) {
        _state.value = _state.value.copy(mobileNumber = mobileNumber)

    }
    private fun updatePassword(password: String) {
        _state.value = _state.value.copy(password = password)
    }
    private fun togglePassword() {
        _state.value = _state.value.copy(
            isPasswordVisible = !_state.value.isPasswordVisible
        )
    }
    private fun signup(){
        _state.value = _state.value.copy(signupSuccess = true)
    }
    private fun login() {
        viewModelScope.launch {
            if (!validateLogin()) return@launch

            _state.value = _state.value.copy(isLoading = true)

            delay(2000)
            // for now
            _state.value = _state.value.copy(
                isLoading = false,
                loginsuccess = true
            )
        }
    }

}
