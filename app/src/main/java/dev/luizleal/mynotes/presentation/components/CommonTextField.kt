package dev.luizleal.mynotes.presentation.components

import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

@Composable
fun CommonTextField(
    modifier: Modifier = Modifier,
    state: TextFieldState,
    placeholder: @Composable () -> Unit,
    textStyle: TextStyle = LocalTextStyle.current,
    singleLine: Boolean = false,
    enabled: Boolean = true,
    maxLines: Int? = null,
) {

    val lineLimits = when {
        maxLines != null -> TextFieldLineLimits.MultiLine(maxLines)
        singleLine -> TextFieldLineLimits.SingleLine
        else -> TextFieldLineLimits.Default
    }

    TextField(
        state = state,
        placeholder = placeholder,
        textStyle = textStyle,
        colors = TextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.onBackground,
            unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
            disabledTextColor = MaterialTheme.colorScheme.onBackground,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            errorContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        enabled = enabled,
        lineLimits = lineLimits,
        modifier = modifier
    )
}