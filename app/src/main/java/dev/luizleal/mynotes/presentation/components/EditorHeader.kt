package dev.luizleal.mynotes.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.input.UndoState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
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
    undoState: UndoState,
    readOnly: Boolean,
    onNavigateBack: () -> Unit,
    onViewModeChange: () -> Unit,
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

        if (readOnly) {
            IconButton(
                onClick = onViewModeChange,
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = MaterialTheme.colorScheme.onBackground,
                    disabledContentColor = MaterialTheme.colorScheme.onSurface
                )
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_edit),
                    contentDescription = "Edit note"
                )
            }
        } else {
            IconButton(
                enabled = undoState.canUndo,
                onClick = {
                    undoState.undo()
                },
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = MaterialTheme.colorScheme.onBackground,
                    disabledContentColor = MaterialTheme.colorScheme.onSurface
                )
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_undo_left),
                    contentDescription = "Undo"
                )
            }
            IconButton(
                enabled = undoState.canRedo,
                onClick = {
                    undoState.redo()
                },
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = MaterialTheme.colorScheme.onBackground,
                    disabledContentColor = MaterialTheme.colorScheme.onSurface
                )
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_undo_right),
                    contentDescription = "Undo"
                )
            }
            IconButton(
                onClick = onViewModeChange,
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = MaterialTheme.colorScheme.onBackground,
                    disabledContentColor = MaterialTheme.colorScheme.onSurface
                )
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_lock),
                    contentDescription = "Lock editor"
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
}

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
private fun EditorHeaderPreview() {
    val state = rememberTextFieldState()
    EditorHeader(
        onNavigateBack = {},
        undoState = state.undoState,
        readOnly = false,
        onViewModeChange = {},
        onSave = {}
    )
}