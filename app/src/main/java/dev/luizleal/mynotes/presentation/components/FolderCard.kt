package dev.luizleal.mynotes.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.luizleal.mynotes.R
import dev.luizleal.mynotes.domain.model.Folder
import dev.luizleal.mynotes.domain.model.FolderColor
import dev.luizleal.mynotes.util.formatTo
import java.time.LocalDateTime

@Composable
fun FolderCard(
    modifier: Modifier = Modifier,
    folder: Folder,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .clip(ShapeDefaults.Small)
            .background(MaterialTheme.colorScheme.surface)
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {

        FolderIcon(
            color = folder.color,
            modifier = Modifier
                //.align(Alignment.CenterHorizontally)
                .size(92.dp)
        )
        Text(
            text = folder.name,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
            ),
            overflow = TextOverflow.Ellipsis,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = folder.createdAt?.formatTo() ?: "",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.labelMedium.copy(
                fontWeight = FontWeight.Normal
            )
        )
    }
}

@Composable
fun FolderIcon(
    modifier: Modifier = Modifier,
    color: FolderColor
) {
    Box(
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(R.drawable.ui_folder_back),
            contentDescription = null,
            tint = color.darken(),
            modifier = Modifier.fillMaxSize()
        )
        Icon(
            painter = painterResource(R.drawable.ui_folder_front),
            contentDescription = null,
            tint = Color(color.hex),
            modifier = Modifier.fillMaxSize()
        )
    }
}


@Preview
@Composable
private fun FolderCardPreview() {
    FolderCard(
        onClick = {},
        folder = Folder(
            name = "Estudos",
            color = FolderColor.BLUE,
            createdAt = LocalDateTime.now()
        )
    )
}