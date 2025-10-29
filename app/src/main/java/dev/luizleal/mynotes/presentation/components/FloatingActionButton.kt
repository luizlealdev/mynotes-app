package dev.luizleal.mynotes.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.luizleal.mynotes.R

@Composable
fun FloatingActionButton(
    modifier: Modifier = Modifier,
    expanded: Boolean = false,
    onFabClick: () -> Unit,
    onOptionClick: (String) -> Unit
) {

    val fabRotation by animateFloatAsState(
        targetValue = if (expanded) 45f else 0f,
        animationSpec = tween(durationMillis = 200),
        label = "fabRotation" //usado para debug
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        AnimatedVisibility(
            visible = expanded,
            enter = fadeIn(tween(200)) + slideInVertically(
                initialOffsetY = { it / 2 }, // começa de metade da altura (desliza de baixo pra cima)
                animationSpec = tween(200)  // duração da entrada
            ),
            exit = fadeOut(tween(150)) + slideOutVertically(
                targetOffsetY = { it / 2 }, // desliza metade pra baixo ao sair
                animationSpec = tween(150) // duração da saída
            ),
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                FabOption(
                    icon = R.drawable.ic_note_add,
                    label = "Add note",
                    onClick = {
                        onOptionClick("AddNote")
                    }
                )
                FabOption(
                    icon = R.drawable.ic_folder,
                    label = "Add folder",
                    onClick = {
                        onOptionClick("AddFolder")
                    }
                )
            }
        }

        Surface(
            onClick = onFabClick,
            color = MaterialTheme.colorScheme.primary,
            shape = CircleShape,
            shadowElevation = 6.dp,
            modifier = modifier.size(62.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_add),
                contentDescription = "Add new note",
                tint = Color.White,
                modifier = Modifier
                    .padding(18.dp)
                    .rotate(fabRotation)
            )
        }
    }
}

@Composable
private fun FabOption(
    @DrawableRes icon: Int,
    label: String,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        color = MaterialTheme.colorScheme.surface,
        shape = CircleShape,
        //shadowElevation = 8.dp,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onTertiary),
        modifier = Modifier.size(48.dp)
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = label,
            tint = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(12.dp)
        )
    }
}

@Preview()
@Composable
private fun FloatingActionButtonPreview() {
    FloatingActionButton(
        expanded = true,
        onFabClick = {},
        onOptionClick = {}
    )
}