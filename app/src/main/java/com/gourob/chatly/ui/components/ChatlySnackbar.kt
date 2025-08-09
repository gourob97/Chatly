package com.gourob.chatly.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import com.gourob.chatly.core.ui.SnackbarType

@Composable
fun ChatlySnackbar(
    modifier: Modifier = Modifier,
    snackbarData: SnackbarData,
    snackbarType: SnackbarType = SnackbarType.INFO,
    shape: Shape = SnackbarDefaults.shape,
) {
    val containerColor = when (snackbarType) {
        SnackbarType.SUCCESS -> Color(0xFF4CAF50)
        SnackbarType.ERROR -> MaterialTheme.colorScheme.error
        SnackbarType.WARNING -> Color(0xFFFF9800)
        SnackbarType.INFO -> MaterialTheme.colorScheme.inverseSurface
    }
    
    val contentColor = when (snackbarType) {
        SnackbarType.SUCCESS -> Color.White
        SnackbarType.ERROR -> MaterialTheme.colorScheme.onError
        SnackbarType.WARNING -> Color.White
        SnackbarType.INFO -> MaterialTheme.colorScheme.inverseOnSurface
    }

    val actionColor = when (snackbarType) {
        SnackbarType.SUCCESS -> Color.White
        SnackbarType.ERROR -> MaterialTheme.colorScheme.onError
        SnackbarType.WARNING -> Color.White
        SnackbarType.INFO -> MaterialTheme.colorScheme.inverseOnSurface
    }
    
    Snackbar(
        snackbarData = snackbarData,
        modifier = modifier,
        shape = shape,
        containerColor = containerColor,
        contentColor = contentColor,
        actionColor = actionColor
    )
}