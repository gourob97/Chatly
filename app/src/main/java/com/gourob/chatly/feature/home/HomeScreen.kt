package com.gourob.chatly.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gourob.chatly.feature.auth.AuthenticationViewModel

@Composable
fun HomeScreen(
    viewModel: AuthenticationViewModel
) {
    // Add logging to confirm we reached the home screen
    androidx.compose.runtime.LaunchedEffect(Unit) {
        println("HomeScreen - Successfully navigated to HomeScreen!")
        println("HomeScreen - User is authenticated and on home screen")
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to Chatly!", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = {
            println("HomeScreen - Logout button clicked")
            viewModel.logout()
        }) {
            Text("Logout")
        }
    }
}

