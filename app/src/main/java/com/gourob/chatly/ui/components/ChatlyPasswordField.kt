package com.gourob.chatly.ui.components

import android.opengl.Visibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.gourob.chatly.R.drawable
import com.gourob.chatly.ui.theme.ChatlyTheme

@Composable
fun ChatlyPasswordField(
    modifier: Modifier = Modifier,
    showPassword: Boolean,
    text: String,
    onTextChange: (String) -> Unit,
    onToggleVisibility: () -> Unit,
    label: String = "Password",
) {
    OutlinedTextField(
        value = text,
        onValueChange = onTextChange,
        label = { Text(label) },
        modifier = modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions.Default.copy(
            capitalization = KeyboardCapitalization.None,
            autoCorrectEnabled = false,
            keyboardType = KeyboardType.Password
        ),
        visualTransformation = if (showPassword) VisualTransformation.None else
            PasswordVisualTransformation(),
        shape = RoundedCornerShape(20.dp),
        singleLine = true,
        trailingIcon = {
            if(text.isNotBlank()) {
                Icon(
                    painter = if (showPassword) painterResource(drawable.visibility)
                    else painterResource(drawable.visibility_off),
                    contentDescription = if (showPassword) "Hide password" else "Show password",
                    modifier = Modifier.clickable {
                        onToggleVisibility()
                    }
                )
            } else {
                Box {

                }
            }
        }
    )
}


@ChatlyPreviewsAllCombinations
@Composable
private fun ChatlyPasswordFieldPreview() {
    ChatlyTheme {
        Surface {
            ChatlyPasswordField(
                text = "",
                onTextChange = {},
                showPassword = false,
                onToggleVisibility = {}
            )
        }
    }
}