package dev.luizleal.mynotes.presentation.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import dev.luizleal.mynotes.presentation.components.FloatingActionButton
import dev.luizleal.mynotes.presentation.components.NoteCard
import dev.luizleal.mynotes.presentation.components.SearchBar
import dev.luizleal.mynotes.presentation.theme.MyNotesTheme
import dev.luizleal.mynotes.presentation.viewmodel.NoteViewModel

@Composable
fun HomeScreen(
    onNavigateToAddNote: () -> Unit,
    noteViewModel: NoteViewModel = hiltViewModel()
) {

    val noteListState = noteViewModel.noteListState.collectAsState()
    val noteListStateValue = noteListState.value

    var isFabExpanded by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                expanded = isFabExpanded,
                onFabClick = {
                    isFabExpanded = !isFabExpanded
                },
                onOptionClick = { option ->
                    onNavigateToAddNote()
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

            //Spacer(modifier = Modifier.height(20.dp))

            SearchBar()

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
                                    onClick = {}
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
            onNavigateToAddNote = {}
        )
    }
}