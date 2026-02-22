package com.example.ma7faza.navigation

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.ma7faza.presentation.auth.login.LoginScreen
import com.example.ma7faza.presentation.splash.SplashScreen
import com.example.ma7faza.presentation.auth.register.RegisterScreen
import com.example.ma7faza.presentation.history.HistoryScreen
import com.example.ma7faza.presentation.home.HomeScreen
import com.example.ma7faza.presentation.profile.ProfileScreen
import com.example.ma7faza.presentation.transfer.TransferScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    // ---------------- Navigation Lambdas ----
    val openLogin: () -> Unit = {navController.navigate(Screen.Login.route){
        popUpTo(Screen.Splash.route) { inclusive = true }
    } }
    val openRegister: () -> Unit = {navController.navigate(Screen.Register.route)}
    val openHome: () -> Unit = {
        navController.navigate(Screen.Home.route) {
            popUpTo(Screen.Login.route) { inclusive = true }
            popUpTo(Screen.Register.route) { inclusive = true }
        }
    }
    val backToLogin: () -> Unit = {
        navController.navigate(Screen.Login.route) {
            popUpTo(Screen.Register.route) { inclusive = true }
        }
    }
    val openTransfer: () -> Unit = {navController.navigate(Screen.Transfer.route)}
    val openHistory: () -> Unit = {navController.navigate(Screen.History.route)}
    val openProfile: () -> Unit = {navController.navigate(Screen.Profile.route)}


    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {

        composable(Screen.Splash.route) {
            SplashScreen(onOpenApp = openLogin)
        }
        composable(Screen.Login.route) {
            LoginScreen(onLoginSuccess = openHome,
                onSignUpClick = openRegister)
        }
        composable(Screen.Register.route) {
            RegisterScreen(
                onBackClick = backToLogin,
                onRegisterSuccess = openHome
            )
        }
        composable(Screen.Home.route) {HomeScreen(onTransferClick = openTransfer, onHistoryClick = openHistory, onProfileClick = openProfile)}
        composable(Screen.Transfer.route) {TransferScreen(onProfileClick = openProfile, onHistoryClick = openHistory, onHomeClick = openHome) }
        composable(Screen.History.route) { HistoryScreen(onProfileClick = openProfile, onTransferClick = openTransfer, onHomeClick = openHome) }
        composable(Screen.Profile.route) {ProfileScreen(onHistoryClick = openHistory, onHomeClick = openHome, onTransferClick = openTransfer) }
    }
}
