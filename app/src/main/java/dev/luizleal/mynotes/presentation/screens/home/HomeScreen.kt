package dev.luizleal.mynotes.presentation.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
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
import dev.luizleal.mynotes.presentation.components.CustomFloatingActionButton
import dev.luizleal.mynotes.presentation.components.FabOptions
import dev.luizleal.mynotes.presentation.components.FolderCard
import dev.luizleal.mynotes.presentation.components.FolderSheet
import dev.luizleal.mynotes.presentation.components.NoteCard
import dev.luizleal.mynotes.presentation.components.SearchBar
import dev.luizleal.mynotes.presentation.theme.MyNotesTheme
import dev.luizleal.mynotes.presentation.viewmodel.FolderViewModel
import dev.luizleal.mynotes.presentation.viewmodel.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToAddNote: () -> Unit,
    onNavigateToNoteDetails: (Long) -> Unit,
    noteViewModel: NoteViewModel = hiltViewModel(),
    folderViewModel: FolderViewModel = hiltViewModel()
) {

    val noteListState = noteViewModel.noteListState.collectAsState()
    val noteListStateValue = noteListState.value

    val folderListState = folderViewModel.folderListState.collectAsState()
    val folderListStateValue = folderListState.value

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

        /**
         * Devido a impossibilidade de colocar uma `LazyList` dentro de um `Column` escrolável, é
         * necessário fazer essa GAMBIARRA: envolver tudo em um `LazyVerticalStaggeredGrid` e os
         * componentes da UI que não fazem parte da lista de folders e notes, adicionar em um
         * `item`. É mais feio? Sim, mas também é funcional.
         */
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Adaptive(180.dp),
            modifier = Modifier
                .consumeWindowInsets(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalItemSpacing = 6.dp,
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            contentPadding = PaddingValues(top = 16.dp, bottom = 16.dp)
        ) {
            val folders = folderListStateValue.data.orEmpty()
            val notes = noteListStateValue.data.orEmpty()

            item(
                span = StaggeredGridItemSpan.FullLine
            ) {
                Text(
                    text = "My Notes",
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            item(
                span = StaggeredGridItemSpan.FullLine
            ) {
                SearchBar(
                    onTextChange = { query ->
                        if (query.isNotEmpty()) {
                            /**
                             * fazer isso é ruim, mas como é um app que usa banco de dados local
                             * e que eu não vou dar continuidade, vou deixar assim por enquanto rs
                             */
                            noteViewModel.searchNotes(query)
                            folderViewModel.searchFolders(query)
                        } else {
                            noteViewModel.getAllNotes()
                        }
                    },
                    modifier = Modifier.padding(bottom = 20.dp)
                )
            }

            items(
                items = folders,
                key = { "folder-${it.id}" }
            ) { folder ->
                FolderCard(
                    folder = folder,
                    onClick = {

                    },
                    modifier = Modifier.animateItem()
                )
            }

            items(
                items = notes,
                key = { "note-${it.id}" }
            ) { note ->
                NoteCard(
                    note = note,
                    onClick = {
                        onNavigateToNoteDetails(note.id)
                    },
                    modifier = Modifier.animateItem()
                )
            }

            if (notes.isEmpty() && folders.isEmpty() && noteListStateValue.isLoading) {
                item(
                    span = StaggeredGridItemSpan.FullLine
                ) {
                    Text(
                        text = "Nothing have been found",
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                    )
                }
            }

        }

        //melhor fora do grid
        FolderSheet(
            sheetState = folderSheetState,
            open = showFolderSheet,
            onOpenChange = {
                showFolderSheet = !showFolderSheet
            },
            onConfirmButton = { createdFolder ->
                //Atualiza se existir e cria se não existir, blz?
                folderViewModel.insertFolder(createdFolder)
                showFolderSheet = false
            }
        )

        when {
            noteListStateValue.isLoading && folderListStateValue.isLoading -> {
                //TODO: IMPLEMENT LOADING
            }

            noteListStateValue.error != null -> {
                //TODO: IMPLEMENT ERROR
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