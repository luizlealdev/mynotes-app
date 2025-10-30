package dev.luizleal.mynotes.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import dev.luizleal.mynotes.R

@Composable
fun EditorHeader(
    modifier: Modifier = Modifier,
    onNavigateBack: () -> Unit,
    onUndo: () -> Unit,
    onRedo: () -> Unit,
    onSave: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {
        IconButton(
            onClick = onNavigateBack
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_arrow_left),
                contentDescription = "Go back",
                tint = MaterialTheme.colorScheme.onBackground,
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        IconButton(
            onClick = onUndo
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_undo_left),
                contentDescription = "Undo",
                tint = MaterialTheme.colorScheme.onBackground
            )
        }

        IconButton(
            onClick = onRedo
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_undo_right),
                contentDescription = "Undo",
                tint = MaterialTheme.colorScheme.onBackground
            )
        }

        TextButton(
            onClick = onSave
        ) {
            Text(
                text = "Save",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun EditorHeaderPreview() {
    EditorHeader(
        onNavigateBack = {},
        onUndo = {},
        onRedo = {},
        onSave = {}
    )
}