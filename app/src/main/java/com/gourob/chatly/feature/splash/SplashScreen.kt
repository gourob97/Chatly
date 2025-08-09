package com.gourob.chatly.feature.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gourob.chatly.R
import com.gourob.chatly.ui.theme.ChatlyTextStyles
import com.gourob.chatly.ui.theme.ChatlyTheme
import com.gourob.chatly.ui.theme.SplashBlue
import com.gourob.chatly.ui.theme.SplashPurple
import com.gourob.chatly.ui.theme.SplashTeal
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun SplashScreen(onSplashFinished: () -> Unit) {
    val scaleAnimation = remember { Animatable(0f) }
    val alphaAnimation = remember { Animatable(0f) }
    val floatAnimation = remember { Animatable(0f) }

    // Beautiful gradient background
    val gradientColors = listOf(SplashTeal, SplashBlue, SplashPurple)

    LaunchedEffect(Unit) {
        // Start scale animation for icon
        scaleAnimation.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing)
        )

        // Start alpha animation for text
        alphaAnimation.animateTo(
            targetValue = 1f, animationSpec = tween(durationMillis = 800, delayMillis = 300)
        )

        // Start floating animation (don't await infinite animation)
        launch {
            floatAnimation.animateTo(
                targetValue = 1f, animationSpec = infiniteRepeatable(
                    animation = tween(2000), repeatMode = RepeatMode.Reverse
                )
            )
        }

        // Wait for animations and show splash
        delay(2500)
        onSplashFinished()
    }



    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(Color(0xFFB2DFDB), Color(0xFF80CBC4)),
                    center = Offset(36f, 36f),
                    radius = 100f
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Icon background circle with subtle animation

            Image(
                painter = painterResource(R.drawable.chatly_logo),
                modifier = Modifier.size(160.dp),
                contentDescription = "Chat Icon"
            )
            Spacer(modifier = Modifier.height(32.dp))

            // App name with enhanced styling
            Text(
                text = "Chatly", style = ChatlyTextStyles.appTitle.copy(
                    color = Color.White,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp
                ), modifier = Modifier.alpha(alphaAnimation.value)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Tagline with elegant styling
            Text(
                text = "Connect • Chat • Share", style = ChatlyTextStyles.bodyText.copy(
                    color = Color.White.copy(alpha = 0.9f),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    letterSpacing = 1.sp
                ), modifier = Modifier.alpha(alphaAnimation.value)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SplashScreenPreview() {
    ChatlyTheme { Surface { SplashScreen(onSplashFinished = {}) } }
}
