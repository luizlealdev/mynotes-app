package dev.luizleal.mynotes.presentation.screens.note

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NoteDetailsScreen(
    noteId: Long,
    onNavigateBack: () -> Unit,
) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .consumeWindowInsets(innerPadding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text("Detalhes da nota")
        }
    }
}