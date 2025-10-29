package dev.luizleal.mynotes.util

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally

object ScreenAnimations {

    fun slideInRight() = slideInHorizontally(initialOffsetX = { it }) + fadeIn()
    fun slideInLeft() = slideInHorizontally(initialOffsetX = { -it }) + fadeIn()
    fun slideOutRight() = slideOutHorizontally(targetOffsetX = { -it }) + fadeOut()
    fun slideOutLeft() = slideOutHorizontally(targetOffsetX = { it }) + fadeOut()
}