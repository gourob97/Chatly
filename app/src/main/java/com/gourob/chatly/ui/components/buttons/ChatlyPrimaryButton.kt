package com.gourob.chatly.ui.components.buttons

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.gourob.chatly.ui.components.ChatlyPreviewsAllCombinations

@Composable
fun ChatlyPrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(text)
    }
}


@ChatlyPreviewsAllCombinations
@Composable
private fun ChatlyPrimaryButtonPreview() {
    ChatlyPrimaryButton(
        text = "Sign in",
        onClick = {}
    )
}