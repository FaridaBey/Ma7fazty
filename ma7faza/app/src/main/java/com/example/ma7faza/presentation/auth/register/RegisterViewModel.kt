package com.example.ma7faza.presentation.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ma7faza.presentation.auth.register.RegisterIntent
import com.example.ma7faza.presentation.auth.register.RegisterState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay


class registerViewModel : ViewModel() {
    private val _state = MutableStateFlow(RegisterState())
    val state: StateFlow<RegisterState> = _state.asStateFlow()

    //     checks for an intent
    fun processIntent(intent: RegisterIntent) {
        when (intent) {
            is RegisterIntent.MobileNumberChanged -> updateMobile(intent.mobileNumber)
            is RegisterIntent.PasswordChanged -> updatePassword(intent.password)
            is RegisterIntent.NameChanged -> updateName(intent.name)
            is RegisterIntent.TogglePasswordVisibility -> togglePassword()
            is RegisterIntent.CreateAccountClicked -> createAccount()
            is RegisterIntent.BackClicked -> back()

        }
    }

    private fun updateMobile(mobileNumber: String) {
        _state.value = _state.value.copy(mobileNumber = mobileNumber)
    }
    private fun updateName(name: String) {
        _state.value = _state.value.copy(name = name)
    }

    private fun updatePassword(password: String) {
        _state.value = _state.value.copy(password = password)
    }

    private fun togglePassword() {
        _state.value = _state.value.copy(
            isPasswordVisible = !_state.value.isPasswordVisible
        )
    }

    private fun isValidPhoneNumber(mobileNumber: String): Boolean {
        val regex = Regex("^01[0|1|2|5]\\d{8}$")
        return regex.matches(mobileNumber)
    }
    private fun isValidPassword(password: String): Boolean {
        val regex = Regex("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")
        return regex.matches(password)
    }

    private fun validateRegistration(): Boolean {
        val passwordRules = "Password must be at least 8 characters long, " +
                "contain at least one uppercase letter, one lowercase letter, " +
                "one number, and one special character (@\$!%*?&)."

        val mobileError = if (_state.value.mobileNumber.isEmpty()) {
            "Mobile number required"
        } else if (!isValidPhoneNumber(_state.value.mobileNumber)) {
            "Invalid phone number"
        } else {
            null
        }

        val passwordError = if (_state.value.password.isEmpty()) {
            "Password required"
        } else if (!isValidPassword(_state.value.password)) {
            "Invalid password: $passwordRules"
        } else {
            null
        }
        val nameError = if (_state.value.name.isEmpty()) {
            "Name required"
        } else {
            null
        }

        // Update state with errors
        _state.value = _state.value.copy(
            mobileError = mobileError,
            passwordError = passwordError,
            nameError = nameError
        )

        // Return true if all valid, false if any error
        return mobileError == null && passwordError == null && nameError == null
    }


    private fun createAccount(){

        viewModelScope.launch {
            if (!validateRegistration()) return@launch
            _state.value = _state.value.copy(isLoading = true)

            delay(2000)
            //for now
            _state.value = _state.value.copy(
                isLoading = false,
                createsuccess = true
            )
        }
    }
    private fun back(){
        _state.value = _state.value.copy(backclick = true)
    }
}
