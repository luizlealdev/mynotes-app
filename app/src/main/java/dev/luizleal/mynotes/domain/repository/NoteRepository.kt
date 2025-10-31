package dev.luizleal.mynotes.domain.repository

import dev.luizleal.mynotes.domain.model.Note

interface NoteRepository {

    suspend fun insertNote(note: Note)
    suspend fun updateNote(note: Note)
    suspend fun deleteNote(note: Note)
    fun getAllNotes(): List<Note?>
    fun getNoteById(id: Long): Note?
    fun getNotesByFolderId(id: Long): List<Note?>
    fun searchNotes(query: String): List<Note?>
}