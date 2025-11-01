package dev.luizleal.mynotes.presentation.screens.note

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import dev.luizleal.mynotes.presentation.components.CustomAlertDialog
import dev.luizleal.mynotes.presentation.components.EditorFields
import dev.luizleal.mynotes.presentation.components.EditorHeader
import dev.luizleal.mynotes.presentation.theme.MyNotesTheme
import dev.luizleal.mynotes.presentation.viewmodel.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteScreen(
    onNavigateBack: () -> Unit,
    noteViewModel: NoteViewModel = hiltViewModel()
) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

    val scrollState = rememberScrollState()
    LaunchedEffect(content) {
        scrollState.animateScrollTo(scrollState.maxValue)
    }

    var leaveScreenAlertDialog by remember { mutableStateOf(false) }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .consumeWindowInsets(innerPadding)
                .fillMaxSize()
                .verticalScroll(scrollState)
                .imePadding()
                .padding(16.dp)
        ) {
            EditorHeader(
                onNavigateBack = {
                    if (title.isNotBlank() || content.isNotEmpty()) {
                        leaveScreenAlertDialog = true
                    } else {
                        onNavigateBack()
                    }
                },
                onSave = {
                    noteViewModel.insertNote(title, content)
                    onNavigateBack()
                },
                onUndo = {},
                onRedo = {}
            )
            EditorFields(
                title = title,
                onTitleChange = { value ->
                    title = value
                },
                content = content,
                onContentChange = { value ->
                    content = value
                }
            )
        }

        when {
            leaveScreenAlertDialog -> {
                CustomAlertDialog(
                    title = "Discard Changes?",
                    description = "All unsaved changes on this note will be permanently lost.",
                    onConfirmText = "Continue Editing",
                    onDismissText = "Discard",
                    onConfirm = {
                        leaveScreenAlertDialog = false
                    },
                    onDismiss = {
                        leaveScreenAlertDialog = false // <- isso serve pra fechar o AlertDialog antes de voltar para a tela inicial
                        onNavigateBack()
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AddNoteScreenPreview() {
    MyNotesTheme {
        AddNoteScreen(
            onNavigateBack = {}
        )
    }
}

