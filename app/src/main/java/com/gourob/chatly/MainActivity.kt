package com.gourob.chatly

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.gourob.chatly.core.ui.UiState
import com.gourob.chatly.core.ui.UiStateManager
import com.gourob.chatly.navigation.Navigation
import com.gourob.chatly.ui.components.ChatlySnackbar
import com.gourob.chatly.ui.components.LoadingOverlay
import com.gourob.chatly.ui.theme.ChatlyTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var uiStateManager: UiStateManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChatlyTheme {
                val snackbarHostState = remember { SnackbarHostState() }
                val uiState by uiStateManager.uiState.collectAsState()
                val snackbarMessage by uiStateManager.snackbarMessage.collectAsState()

                // Handle snackbar messages
                LaunchedEffect(snackbarMessage) {
                    snackbarMessage?.let { message ->
                        val duration = when (message.duration) {
                            com.gourob.chatly.core.ui.SnackbarDuration.SHORT -> SnackbarDuration.Short
                            com.gourob.chatly.core.ui.SnackbarDuration.LONG -> SnackbarDuration.Long
                            com.gourob.chatly.core.ui.SnackbarDuration.INDEFINITE -> SnackbarDuration.Indefinite
                        }

                        snackbarHostState.showSnackbar(
                            message = message.message,
                            actionLabel = message.actionLabel,
                            duration = duration
                        )
                        uiStateManager.clearSnackbar()
                    }
                }

                Scaffold { paddingValues ->
                    // Navigation without padding for true edge-to-edge
                    Navigation(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(
                                top = paddingValues.calculateTopPadding()
                            ),
                    )

                    // Snackbar host positioned at bottom
                    SnackbarHost(
                        hostState = snackbarHostState,
                        //modifier = Modifier.align(androidx.compose.ui.Alignment.BottomCenter),
                        snackbar = { snackbarData ->
                            ChatlySnackbar(
                                snackbarData = snackbarData,
                                snackbarType = snackbarMessage?.type
                                    ?: com.gourob.chatly.core.ui.SnackbarType.INFO
                            )
                        }
                    )

                    // Show loading overlay when loading
                    if (uiState is UiState.Loading) {
                        LoadingOverlay(
                            message = (uiState as UiState.Loading).message
                        )
                    }
                }
            }
        }
    }
}