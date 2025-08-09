package com.gourob.chatly.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * Chatly-specific text styles for consistent typography throughout the app
 */
object ChatlyTextStyles {
    
    // App-specific text styles
    val appTitle: TextStyle
        @Composable get() = MaterialTheme.typography.displayMedium.copy(
            fontWeight = FontWeight.Bold,
            letterSpacing = (-0.5).sp
        )
    
    val screenTitle: TextStyle
        @Composable get() = MaterialTheme.typography.headlineLarge.copy(
            fontWeight = FontWeight.SemiBold
        )
    
    val sectionHeader: TextStyle
        @Composable get() = MaterialTheme.typography.headlineMedium.copy(
            fontWeight = FontWeight.Medium
        )
    
    val cardTitle: TextStyle
        @Composable get() = MaterialTheme.typography.titleLarge.copy(
            fontWeight = FontWeight.SemiBold
        )
    
    val cardSubtitle: TextStyle
        @Composable get() = MaterialTheme.typography.titleMedium.copy(
            fontWeight = FontWeight.Normal
        )
    
    val buttonText: TextStyle
        @Composable get() = MaterialTheme.typography.labelLarge.copy(
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 0.5.sp
        )
    
    val inputLabel: TextStyle
        @Composable get() = MaterialTheme.typography.labelMedium.copy(
            fontWeight = FontWeight.Medium
        )
    
    val inputText: TextStyle
        @Composable get() = MaterialTheme.typography.bodyLarge.copy(
            fontWeight = FontWeight.Normal
        )
    
    val bodyText: TextStyle
        @Composable get() = MaterialTheme.typography.bodyMedium.copy(
            fontWeight = FontWeight.Normal
        )
    
    val captionText: TextStyle
        @Composable get() = MaterialTheme.typography.bodySmall.copy(
            fontWeight = FontWeight.Normal
        )
    
    val errorText: TextStyle
        @Composable get() = MaterialTheme.typography.bodySmall.copy(
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.error
        )
    
    val successText: TextStyle
        @Composable get() = MaterialTheme.typography.bodySmall.copy(
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.primary
        )
    
    val linkText: TextStyle
        @Composable get() = MaterialTheme.typography.bodyMedium.copy(
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.primary
        )
    
    val tabText: TextStyle
        @Composable get() = MaterialTheme.typography.labelLarge.copy(
            fontWeight = FontWeight.Medium
        )
    
    val chipText: TextStyle
        @Composable get() = MaterialTheme.typography.labelMedium.copy(
            fontWeight = FontWeight.Medium
        )
    
    val dialogTitle: TextStyle
        @Composable get() = MaterialTheme.typography.headlineSmall.copy(
            fontWeight = FontWeight.SemiBold
        )
    
    val dialogBody: TextStyle
        @Composable get() = MaterialTheme.typography.bodyMedium.copy(
            fontWeight = FontWeight.Normal
        )
    
    val snackbarText: TextStyle
        @Composable get() = MaterialTheme.typography.bodyMedium.copy(
            fontWeight = FontWeight.Medium
        )
    
    val loadingText: TextStyle
        @Composable get() = MaterialTheme.typography.bodyMedium.copy(
            fontWeight = FontWeight.Medium
        )
}