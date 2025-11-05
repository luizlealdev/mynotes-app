package dev.luizleal.mynotes.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun EditorFields(
    titleState: TextFieldState,
    contentState: TextFieldState,
    readOnly: Boolean
) {

    CommonTextField(
        state = titleState,
        placeholder = {
            Text(
                text = "Note title",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = MaterialTheme.colorScheme.onSurface
            )
        },
        textStyle = TextStyle.Default.copy(
            fontSize = MaterialTheme.typography.displaySmall.fontSize,
            fontWeight = FontWeight.SemiBold
        ),
        enabled = !readOnly,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(0.dp)
            .padding(0.dp)
    )
    CommonTextField(
        state = contentState,
        placeholder = {
            Text(
                text = "Type something... \n\n\n\n\n",
                color = MaterialTheme.colorScheme.onSurface
            )
        },
        maxLines = Int.MAX_VALUE,
        enabled = !readOnly,
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxSize()
    )
}