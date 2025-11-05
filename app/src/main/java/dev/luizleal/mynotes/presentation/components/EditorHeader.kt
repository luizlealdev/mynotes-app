package dev.luizleal.mynotes.presentation.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
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
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
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

        AnimatedContent(
            targetState = readOnly,
            transitionSpec = {
                fadeIn(tween(200)) togetherWith fadeOut(tween(200))
            },
            label = "ViewModeSwitchAnimation"
        ) { isReadOnly ->
            if (isReadOnly) {
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
                Row {
                    //Undo button
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
                    //Redo button
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
                    //Lock button
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
                    //Save button
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