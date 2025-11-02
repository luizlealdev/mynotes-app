package dev.luizleal.mynotes.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.luizleal.mynotes.domain.model.Note
import dev.luizleal.mynotes.presentation.theme.MyNotesTheme
import dev.luizleal.mynotes.util.formatTo
import java.time.LocalDateTime

@Composable
fun NoteCard(
    modifier: Modifier = Modifier,
    note: Note,
    onClick: () -> Unit
) {

    Column(
        modifier = modifier
            .clip(ShapeDefaults.Small)
            .background(MaterialTheme.colorScheme.surface)
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Text(
            text = note.title,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
            ),
        )
        Text(
            text = note.content,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 6,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = note.updatedAt?.formatTo() ?: "",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.labelMedium
        )
    }
}


@Preview
@Composable
private fun NoteCardPreview() {
    MyNotesTheme {
        NoteCard(
            onClick = {},
            note = Note(
                title = "Fazer compras",
                content = "For scenarios where you want to dynamically expand/collapse the text to show/hide the full content, you can combine maxLines and overflow with a state variable (e.g., mutableStateOf) to toggle between Int.MAX_VALUE and a limited number of lines.",
                updatedAt = LocalDateTime.now()
            )
        )
    }
}