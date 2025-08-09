package com.gourob.chatly.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gourob.chatly.feature.auth.AuthScreen
import com.gourob.chatly.feature.auth.AuthScreenType
import com.gourob.chatly.feature.auth.AuthenticationViewModel
import com.gourob.chatly.feature.home.HomeScreen

@Composable
fun Navigation(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()
    val authViewModel: AuthenticationViewModel = hiltViewModel()
    
    // Observe authentication state and handle navigation
    val isAuthenticated by authViewModel.isAuthenticated.collectAsStateWithLifecycle()
    
    // Handle navigation based on authentication state
    LaunchedEffect(isAuthenticated) {
        println("Navigation - isAuthenticated changed to: $isAuthenticated")
        
        if (isAuthenticated) {
            println("Navigation - User is authenticated, navigating to HomeScreen")
            navController.navigate(HomeScreen) {
                popUpTo(LoginScreen) { inclusive = true }
            }
            
            // Show welcome message if user just logged in
            if (authViewModel.uiState.isLoginSuccess) {
                val message = if (authViewModel.uiState.authScreenType == AuthScreenType.LOGIN) {
                    "Welcome back!"
                } else {
                    "Account created successfully! Welcome!"
                }
                authViewModel.showMessage(message, isError = false)
                authViewModel.resetLoginSuccess()
            }
        } else {
            println("Navigation - User is not authenticated, navigating to LoginScreen")
            navController.navigate(LoginScreen) {
                popUpTo(HomeScreen) { inclusive = true }
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = LoginScreen,
        modifier = modifier
    ) {
        composable<LoginScreen> {
            println("Navigation - Composing LoginScreen")
            AuthScreen(viewModel = authViewModel)
        }

        composable<HomeScreen> {
            println("Navigation - Composing HomeScreen")
            HomeScreen(viewModel = authViewModel)
        }
    }
}
