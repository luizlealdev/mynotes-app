package dev.luizleal.mynotes.presentation.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.luizleal.mynotes.presentation.components.FloatingActionButton
import dev.luizleal.mynotes.presentation.components.SearchBar
import dev.luizleal.mynotes.presentation.theme.MyNotesTheme

@Composable
fun HomeScreen(
    onNavigateToAddNote: () -> Unit,
) {

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
                .padding(16.dp)
        ) {
            Text(
                text = "My Notes",
                style = MaterialTheme.typography.displaySmall
            )

            Spacer(modifier = Modifier.height(20.dp))

            SearchBar()
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