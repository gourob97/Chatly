package com.gourob.chatly.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gourob.chatly.LoginScreen
import com.gourob.chatly.RegistrationScreen
import kotlinx.serialization.Serializable

@Composable
fun Navigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = RegistrationRoute,
        modifier = modifier
    ) {
        composable<RegistrationRoute> {
            RegistrationScreen(
                onRegister = {
                    navController.navigate(LoginRoute)
                }
            )
        }

        composable<LoginRoute>{
            LoginScreen()
        }
    }
}




@Serializable
object RegistrationRoute

@Serializable
object LoginRoute