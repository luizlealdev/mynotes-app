package dev.luizleal.mynotes.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import dev.luizleal.mynotes.presentation.screens.home.HomeScreen
import dev.luizleal.mynotes.presentation.screens.note.AddNoteScreen
import dev.luizleal.mynotes.util.ScreenAnimations.slideInLeft
import dev.luizleal.mynotes.util.ScreenAnimations.slideInRight
import dev.luizleal.mynotes.util.ScreenAnimations.slideOutLeft
import dev.luizleal.mynotes.util.ScreenAnimations.slideOutRight


@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Home
    ) {
        composable<Home>(
            popEnterTransition = { slideInLeft() },
            popExitTransition = { slideOutRight() }
        ) {
            HomeScreen(
                onNavigateToAddNote = {
                    navController.navigate(AddNote)
                }
            )
        }

        composable<AddNote>(
            enterTransition = { slideInRight() },
            popExitTransition = { slideOutLeft() }
        ) {
            AddNoteScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable<EditNote>(
            enterTransition = { slideInRight() },
            popExitTransition = { slideOutLeft() }
        ) { backStackEntry ->
            backStackEntry.toRoute<EditNote>()
            TODO("implementa o edit screen aí macho kkkk")
        }
    }
}