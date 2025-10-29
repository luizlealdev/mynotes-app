package dev.luizleal.mynotes.presentation.screens.note

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.luizleal.mynotes.R
import dev.luizleal.mynotes.presentation.theme.MyNotesTheme

@Composable
fun AddNoteScreen(modifier: Modifier = Modifier) {
    Scaffold { innerPadding ->
        Column(
            modifier = modifier
                .consumeWindowInsets(innerPadding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row {
                IconButton(
                    onClick = {
                        //TODO: Voltar para tela inicial
                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_arrow_left),
                        contentDescription = "Go back",
                        tint = MaterialTheme.colorScheme.onBackground,
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                IconButton(
                    onClick = {
                        //TODO: Undo
                    },
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_undo_left),
                        contentDescription = "Undo",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }

                IconButton(
                    onClick = {
                        //TODO: Undo
                    },
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_undo_right),
                        contentDescription = "Undo",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }

                TextButton(
                    onClick = {
                        //TODO: Salvar anotação
                    }
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

@Preview(showBackground = true)
@Composable
private fun AddNoteScreenPreview() {
    MyNotesTheme {
        AddNoteScreen()
    }
}

