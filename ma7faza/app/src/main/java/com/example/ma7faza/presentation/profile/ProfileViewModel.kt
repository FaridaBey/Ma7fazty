package com.example.ma7faza.presentation.profile

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class profileViewModel : ViewModel(){
    private val _state = MutableStateFlow(ProfileState())
    val state: StateFlow<ProfileState> = _state.asStateFlow()
    fun processIntent(intent: ProfileIntent){
        when(intent){
            is ProfileIntent.editName -> {}
            is ProfileIntent.editEmail ->  {}
            is ProfileIntent.ChangePassword -> {}
        }
    }


}