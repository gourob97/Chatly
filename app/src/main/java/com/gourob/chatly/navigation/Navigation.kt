package com.gourob.chatly.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gourob.chatly.LoginScreen
import com.gourob.chatly.RegistrationScreen
import com.gourob.chatly.ui.viewmodel.RegistrationViewModel
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
            val viewModel: RegistrationViewModel = hiltViewModel()
            RegistrationScreen(
                viewModel = viewModel
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