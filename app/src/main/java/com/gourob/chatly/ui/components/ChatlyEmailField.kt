package com.gourob.chatly.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gourob.chatly.ui.theme.ChatlyTheme

@Composable
fun ChatlyEmailField(
    modifier: Modifier = Modifier,
    text: String,
    onTextChange: (String) -> Unit,
    label: String = "Email",
) {
    OutlinedTextField(
        value = text,
        onValueChange = onTextChange,
        label = { Text(label) },
        modifier = modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions.Default.copy(
            capitalization = KeyboardCapitalization.None,
            autoCorrectEnabled = false,
            keyboardType = KeyboardType.Email
        ),
        shape = RoundedCornerShape(20.dp),
        singleLine = true
    )
}

@ChatlyPreviewsAllCombinations
@Composable
private fun ChatlyEmailFieldPreview() {
    ChatlyTheme {
        Surface {
            Column (
                modifier = Modifier.fillMaxSize()
            ){
                ChatlyEmailField(
                    text = "",
                    onTextChange = {},
                    label = "Email",
                )
            }

        }
    }
}





/**
 * Full matrix of previews:
 * - Devices: Phone, Foldable, Tablet
 * - Themes: Light, Dark
 * - Font Sizes: Normal (1.0), Large (1.5), Extra Large (2.0)
 */
@Preview(name = "Phone - Light - Normal", device = "spec:width=411dp,height=891dp", fontScale = 1.0f, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Phone - Light - Large", device = "spec:width=411dp,height=891dp", fontScale = 1.5f, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Phone - Light - Extra Large", device = "spec:width=411dp,height=891dp", fontScale = 2.0f, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Phone - Dark - Normal", device = "spec:width=411dp,height=891dp", fontScale = 1.0f, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Phone - Dark - Large", device = "spec:width=411dp,height=891dp", fontScale = 1.5f, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Phone - Dark - Extra Large", device = "spec:width=411dp,height=891dp", fontScale = 2.0f, uiMode = Configuration.UI_MODE_NIGHT_YES)

@Preview(name = "Foldable - Light - Normal", device = "spec:width=673dp,height=841dp", fontScale = 1.0f, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Foldable - Light - Large", device = "spec:width=673dp,height=841dp", fontScale = 1.5f, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Foldable - Light - Extra Large", device = "spec:width=673dp,height=841dp", fontScale = 2.0f, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Foldable - Dark - Normal", device = "spec:width=673dp,height=841dp", fontScale = 1.0f, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Foldable - Dark - Large", device = "spec:width=673dp,height=841dp", fontScale = 1.5f, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Foldable - Dark - Extra Large", device = "spec:width=673dp,height=841dp", fontScale = 2.0f, uiMode = Configuration.UI_MODE_NIGHT_YES)

@Preview(name = "Tablet - Light - Normal", device = "spec:width=800dp,height=1280dp", fontScale = 1.0f, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Tablet - Light - Large", device = "spec:width=800dp,height=1280dp", fontScale = 1.5f, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Tablet - Light - Extra Large", device = "spec:width=800dp,height=1280dp", fontScale = 2.0f, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Tablet - Dark - Normal", device = "spec:width=800dp,height=1280dp", fontScale = 1.0f, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Tablet - Dark - Large", device = "spec:width=800dp,height=1280dp", fontScale = 1.5f, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Tablet - Dark - Extra Large", device = "spec:width=800dp,height=1280dp", fontScale = 2.0f, uiMode = Configuration.UI_MODE_NIGHT_YES)
annotation class ChatlyPreviewsAllCombinations