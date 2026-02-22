package com.example.ma7faza.presentation.profile

sealed class ProfileIntent {
    object editName : ProfileIntent()
    object editEmail : ProfileIntent()
    object ChangePassword : ProfileIntent()

}