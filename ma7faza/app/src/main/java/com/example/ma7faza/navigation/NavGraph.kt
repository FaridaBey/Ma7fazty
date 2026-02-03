package com.example.ma7faza.navigation

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.ma7faza.presentation.auth.login.LoginScreen
import com.example.ma7faza.presentation.splash.SplashScreen
import com.example.ma7faza.presentation.auth.register.RegisterScreen
import com.example.ma7faza.navigation.screen

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = screen.Splash.route
    ) {
        composable(screen.Splash.route) {
            SplashScreen(onOpenApp = {
                navController.navigate(screen.Login.route)
            })
        }
        composable(screen.Login.route) {
            LoginScreen(onLoginSuccess = {navController.navigate(screen.Home.route)},
                onSignUpClick = {navController.navigate(screen.Register.route)})
        }
        composable(screen.Register.route) {
            RegisterScreen(
                onBackClick = {navController.navigate(screen.Login.route)},
                onRegisterSuccess = {navController.navigate(screen.Home.route)}
            )
        }
        composable(screen.Home.route) {}
        composable(screen.Transfer.route) {}
        composable(screen.History.route) {}
        composable(screen.Profile.route) {}
    }
}
