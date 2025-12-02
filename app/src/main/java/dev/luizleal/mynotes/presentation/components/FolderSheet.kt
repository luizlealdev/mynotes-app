package dev.luizleal.mynotes.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SheetState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.luizleal.mynotes.R
import dev.luizleal.mynotes.domain.model.Folder
import dev.luizleal.mynotes.domain.model.FolderColor
import dev.luizleal.mynotes.presentation.theme.MyNotesTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FolderSheet(
    modifier: Modifier = Modifier,
    folder: Folder? = null,
    open: Boolean,
    sheetState: SheetState,
    onOpenChange: () -> Unit,
    onConfirmButton: (folder: Folder) -> Unit
) {
    var title by remember { mutableStateOf(folder?.name ?: "") }
    var color by remember { mutableStateOf(folder?.color ?: FolderColor.BLUE) }

    if (open) {
        ModalBottomSheet(
            onDismissRequest = onOpenChange,
            sheetState = sheetState,
            modifier = modifier
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = if (folder != null) "Edit folder" else "Create new folder",
                    style = MaterialTheme.typography.displaySmall,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Spacer(modifier = Modifier.height(16.dp))

                Column {
                    Text(
                        text = "Folder name",
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 4.dp)
                    )
                    OutlinedTextField(
                        value = title,
                        onValueChange = { value ->
                            title = value
                        },
                        shape = RoundedCornerShape(12.dp),
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = MaterialTheme.colorScheme.onBackground,
                            unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
                            focusedContainerColor = MaterialTheme.colorScheme.surface,
                            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Color",
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp)
                )
                LazyVerticalGrid(
                    columns = GridCells.FixedSize(48.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(FolderColor.entries) { currentColor ->
                        val darkerColor = lerp(Color.Black, Color(currentColor.hex), 0.75f)

                        Surface(
                            onClick = {
                                color = currentColor
                            },
                            color = Color(currentColor.hex),
                            shape = RoundedCornerShape(12.dp),
                            border = BorderStroke(1.5.dp, darkerColor),
                            modifier = Modifier.size(48.dp)
                        ) {
                            if (currentColor == color) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_check),
                                    contentDescription = currentColor.name,
                                    tint = darkerColor,
                                    modifier = Modifier.padding(12.dp)
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        val createdOrUpdatedFolder = folder?.copy(
                            name = title,
                            color = color
                        ) ?: Folder(
                            name = title,
                            color = color
                        )

                        onConfirmButton(createdOrUpdatedFolder)
                    },
                    contentPadding = PaddingValues(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Create",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = Color.White
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun FolderSheetPreview() {
    MyNotesTheme {
        FolderSheet(
            open = true,
            sheetState = rememberModalBottomSheetState(),
            onOpenChange = {},
            onConfirmButton = {}
        )
    }
}