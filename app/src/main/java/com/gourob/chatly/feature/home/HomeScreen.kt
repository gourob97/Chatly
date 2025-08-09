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
import com.gourob.chatly.ui.theme.ChatlyTextStyles

@Composable
fun HomeScreen(
    viewModel: AuthenticationViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to Chatly!", style = ChatlyTextStyles.appTitle)

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = {
            viewModel.logout()
        }) {
            Text("Logout", style = ChatlyTextStyles.buttonText)
        }
    }
}

