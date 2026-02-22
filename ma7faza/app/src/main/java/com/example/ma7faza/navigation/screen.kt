package com.example.ma7faza.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Login : Screen("login")
    object Register : Screen("register")
    object Home : Screen("home")
    object Transfer : Screen("transfer")
    object History : Screen("history")
    object Profile : Screen("profile")
}