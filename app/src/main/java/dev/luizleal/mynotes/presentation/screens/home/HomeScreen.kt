package dev.luizleal.mynotes.presentation.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import dev.luizleal.mynotes.presentation.components.FabOptions
import dev.luizleal.mynotes.presentation.components.CustomFloatingActionButton
import dev.luizleal.mynotes.presentation.components.FolderSheet
import dev.luizleal.mynotes.presentation.components.NoteCard
import dev.luizleal.mynotes.presentation.components.SearchBar
import dev.luizleal.mynotes.presentation.theme.MyNotesTheme
import dev.luizleal.mynotes.presentation.viewmodel.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToAddNote: () -> Unit,
    onNavigateToNoteDetails: (Long) -> Unit,
    noteViewModel: NoteViewModel = hiltViewModel()
) {

    val noteListState = noteViewModel.noteListState.collectAsState()
    val noteListStateValue = noteListState.value

    var isFabExpanded by remember { mutableStateOf(false) }

    val folderSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    var showFolderSheet by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            CustomFloatingActionButton(
                expanded = isFabExpanded,
                onFabClick = {
                    isFabExpanded = !isFabExpanded
                },
                onOptionClick = { option ->
                    when (option) {
                        FabOptions.ADD_NOTE -> {
                            onNavigateToAddNote()
                        }

                        FabOptions.ADD_FOLDER -> {
                            showFolderSheet = true
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .consumeWindowInsets(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                text = "My Notes",
                style = MaterialTheme.typography.displaySmall
            )

            SearchBar(
                onTextChange = { query ->
                    if (query.isNotEmpty()) {
                        /**
                         * fazer isso é ruim, mas como é um app que usa banco de dados local
                         * e que eu não vou dar continuidade, vou deixar assim por enquanto rs
                         */
                        noteViewModel.searchNotes(query)
                    } else {
                        noteViewModel.getAllNotes()
                    }
                }
            )

            FolderSheet(
                sheetState = folderSheetState,
                open = showFolderSheet,
                onOpenChange = {
                    showFolderSheet = !showFolderSheet
                },
                onConfirmButton = { createdFolder ->
                    //TODO: Criar pasta
                }
            )

            when {
                noteListStateValue.data != null -> {
                    val notes = noteListStateValue.data

                    if (notes.isNotEmpty()) {
                        LazyVerticalStaggeredGrid(
                            modifier = Modifier.fillMaxSize(),
                            columns = StaggeredGridCells.Adaptive(180.dp),
                            verticalItemSpacing = 6.dp,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            items(notes) { note ->
                                NoteCard(
                                    note = note,
                                    onClick = {
                                        onNavigateToNoteDetails(note.id)
                                    }
                                )
                            }
                        }
                    } else {
                        Text("No notes found")
                    }
                }

                noteListStateValue.isLoading -> {
                    //TODO: Implement skeleton loading
                }

                noteListStateValue.error != null -> {
                    Text("Something went wrong...")
                }
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    MyNotesTheme {
        HomeScreen(
            onNavigateToAddNote = {},
            onNavigateToNoteDetails = {}
        )
    }
}