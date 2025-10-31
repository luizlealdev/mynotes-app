package dev.luizleal.mynotes.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.ui.Modifier
import dev.luizleal.mynotes.presentation.navigation.AppNavHost
import dev.luizleal.mynotes.presentation.theme.MyNotesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyNotesTheme {
                Box(
                    modifier = Modifier.Companion.safeDrawingPadding()
                ) {
                    AppNavHost()
                }
            }
        }
    }
}