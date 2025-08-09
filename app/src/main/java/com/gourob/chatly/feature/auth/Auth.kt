package com.gourob.chatly.feature.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gourob.chatly.ui.components.ChatlyEmailField
import com.gourob.chatly.ui.components.ChatlyPasswordField
import com.gourob.chatly.ui.components.buttons.ChatlyPrimaryButton
import com.gourob.chatly.ui.theme.ChatlyTheme

@Composable
fun AuthScreen(
    viewModel: AuthenticationViewModel
) {
    val isLogin = viewModel.uiState.authScreenType == AuthScreenType.LOGIN
    AuthScreenContent(
        isLogin = isLogin,
        uiState = viewModel.uiState,
        onLogin = { viewModel.login() },
        onSignUp = { viewModel.signup() },
        onEmailChange = { viewModel.onEmailChange(it) },
        onPasswordChange = { viewModel.onPasswordChange(it) },
        onToggleLogin = viewModel::toggleAuthScreen
    )
}

@Composable
fun AuthScreenContent(
    uiState: ChatlyLoginUiState,
    isLogin: Boolean,
    onLogin: () -> Unit,
    onSignUp: () -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onToggleLogin: () -> Unit
) {

    var showPassword by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(if (isLogin) "Sign in" else "Sign up")

        Spacer(Modifier.height(5.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            ChatlyEmailField(
                text = uiState.email,
                onTextChange = onEmailChange,
                label = "Email",
            )
            ChatlyPasswordField(
                text = uiState.password,
                onTextChange = onPasswordChange,
                showPassword = showPassword,
                onToggleVisibility = { showPassword = !showPassword}
            )

            ChatlyPrimaryButton(
                text = if (isLogin) "Sign in" else "Sign up",
                onClick = {
                    if(isLogin) {onLogin()} else {onSignUp()}
                }
            )

            Spacer(Modifier.height(5.dp))

            Text(
                if (isLogin) "Don't have an account? Sign up"
                else "Already have an account? Sign in",

                modifier = Modifier.clickable {
                    onToggleLogin()
                }
            )

            Spacer(Modifier.height(10.dp))
            ChatlyPrimaryButton(
                if (isLogin) "Sign in with google"
                else "Sign up with google",
                onClick = {}
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun AuthScreen2Preview() {
    ChatlyTheme {
        Surface {
            AuthScreenContent(
                uiState = ChatlyLoginUiState(),
                isLogin = true,
                onLogin = {},
                onSignUp = {},
                onEmailChange = {},
                onPasswordChange = {},
                onToggleLogin = {}
            )
        }
    }
}