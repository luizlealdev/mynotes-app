package dev.luizleal.mynotes.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import dev.luizleal.mynotes.presentation.screens.home.HomeScreen
import dev.luizleal.mynotes.presentation.screens.note.AddNoteScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Home) {
        composable<Home> {
            HomeScreen()
        }

        composable<AddNote> {
            AddNoteScreen()
        }

        composable<EditNote> { backStackEntry ->
            val addNoteRoute = backStackEntry.toRoute<EditNote>()
            TODO("implementa o edit screen aí macho kkkk")
        }
    }
}