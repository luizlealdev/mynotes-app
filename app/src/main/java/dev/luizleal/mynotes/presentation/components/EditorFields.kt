package dev.luizleal.mynotes.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun EditorFields(
    title: String,
    onTitleChange: (String) -> Unit,
    content: String,
    onContentChange: (String) -> Unit
) {

    CommonTextField(
        value = title,
        onValueChange = onTitleChange,
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
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(0.dp)
            .padding(0.dp)
    )
    CommonTextField(
        value = content,
        onValueChange = onContentChange,
        placeholder = {
            Text(
                text = "Type something...",
                color = MaterialTheme.colorScheme.onSurface
            )
        },
        maxLines = Int.MAX_VALUE,
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxSize()
    )
}