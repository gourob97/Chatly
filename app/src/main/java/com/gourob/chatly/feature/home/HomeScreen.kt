package com.gourob.chatly.feature.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.gourob.chatly.R
import com.gourob.chatly.feature.auth.AuthenticationViewModel
import com.gourob.chatly.ui.theme.ChatlyTextStyles

@Composable
fun HomeScreen(
    viewModel: AuthenticationViewModel,
) {
    var selectedTab by remember { mutableIntStateOf(0) }
    //val currentUser by viewModel.authRepository.currentUser.collectAsStateWithLifecycle()
    
    // Extract username from email
    val username = "Gourob"
    
    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(painter = painterResource(R.drawable.chat_icon), contentDescription = "Chats") },
                    label = { Text("Chats", style = ChatlyTextStyles.tabText) },
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 }
                )
                NavigationBarItem(
                    icon = { Icon(painter = painterResource(R.drawable.person_2), contentDescription = "People") },
                    label = { Text("People", style = ChatlyTextStyles.tabText) },
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile", style = ChatlyTextStyles.tabText) },
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 }
                )
            }
        },
        floatingActionButton = {
            if (selectedTab == 0) { // Only show FAB on Chats tab
                FloatingActionButton(
                    onClick = { /* TODO: Start new chat */ }
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Start Chat")
                }
            }
        }
    ) { paddingValues ->
        when (selectedTab) {
            0 -> ChatsTab(
                username = username,
                modifier = Modifier.padding(paddingValues)
            )
            1 -> PeopleTab(
                modifier = Modifier.padding(paddingValues)
            )
            2 -> ProfileTab(
                username = username,
                userEmail = "gourob@gmail.com",
                onLogout = { viewModel.logout() },
                modifier = Modifier.padding(paddingValues)
            )
        }
    }
}

@Composable
private fun ChatsTab(
    modifier: Modifier = Modifier,
    username: String
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.systemBars)
            .padding(16.dp)
    ) {
        // Header with profile icon and username
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Profile",
                modifier = Modifier.size(32.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = username,
                style = ChatlyTextStyles.screenTitle
            )
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Empty state
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "No chats yet",
                    style = ChatlyTextStyles.sectionHeader
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Start a conversation with your friends",
                    style = ChatlyTextStyles.bodyText,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun PeopleTab(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.systemBars),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Contacts coming soon",
                style = ChatlyTextStyles.sectionHeader
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Find and connect with friends",
                style = ChatlyTextStyles.bodyText,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun ProfileTab(
    username: String,
    userEmail: String,
    onLogout: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.systemBars)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        
        // Profile icon
        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = "Profile Picture",
            modifier = Modifier.size(80.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Username
        Text(
            text = username,
            style = ChatlyTextStyles.screenTitle
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        // Email
        Text(
            text = userEmail,
            style = ChatlyTextStyles.bodyText,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        
        Spacer(modifier = Modifier.height(48.dp))
        
        // Logout button
        Button(
            onClick = onLogout
        ) {
            Text("Logout", style = ChatlyTextStyles.buttonText)
        }
    }
}

