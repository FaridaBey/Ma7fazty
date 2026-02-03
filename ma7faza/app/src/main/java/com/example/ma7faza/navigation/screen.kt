package com.example.ma7faza.navigation

sealed class screen(val route: String) {
    object Splash : screen("splash")
    object Login : screen("login")
    object Register : screen("register")
    object Home : screen("home")
    object Transfer : screen("transfer")
    object History : screen("history")
    object Profile : screen("profile")
}