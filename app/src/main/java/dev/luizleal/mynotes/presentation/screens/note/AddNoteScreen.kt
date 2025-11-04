package dev.luizleal.mynotes.presentation.screens.note

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.input.rememberTextFieldState
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

@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalFoundationApi::class
)
@Composable
fun AddNoteScreen(
    onNavigateBack: () -> Unit,
    noteViewModel: NoteViewModel = hiltViewModel()
) {
    val titleState = rememberTextFieldState()
    val contentState = rememberTextFieldState()

    val scrollState = rememberScrollState()
    LaunchedEffect(contentState.text) {
        /**
         * Verifica se o cursor está no final e só então faz o scroll para baixo
         */
        if (contentState.selection.start == contentState.text.length) {
            scrollState.animateScrollTo(scrollState.maxValue)
        }
    }

    var showExitConfirmation by remember { mutableStateOf(false) }

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
                    if (titleState.text.isNotBlank() || contentState.text.isNotEmpty()) {
                        showExitConfirmation = true
                    } else {
                        onNavigateBack()
                    }
                },
                onSave = {
                    if (titleState.text.isNotBlank() && contentState.text.isNotEmpty()) {
                        noteViewModel.insertNote(
                            title = titleState.text.toString().trim(),
                            content = contentState.text.toString().trim()
                        )
                        onNavigateBack()
                    }
                },
               undoState = contentState.undoState
            )
            EditorFields(
                titleState = titleState,
                contentState = contentState
            )
        }

        when {
            showExitConfirmation -> {
                CustomAlertDialog(
                    title = "Discard Changes?",
                    description = "All unsaved changes on this note will be permanently lost.",
                    onConfirmText = "Continue Editing",
                    onDismissText = "Discard",
                    onConfirm = {
                        showExitConfirmation = false
                    },
                    onDismiss = {
                        showExitConfirmation =
                            false // <- isso serve pra fechar o AlertDialog antes de voltar para a tela inicial
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

