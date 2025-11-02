package dev.luizleal.mynotes.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
object AddNote

@Serializable
data class NoteDetails(val id: Long)