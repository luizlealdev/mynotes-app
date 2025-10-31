package dev.luizleal.mynotes.domain.repository

import dev.luizleal.mynotes.domain.model.Note

interface NoteRepository {

    suspend fun insertNote(note: Note)
    suspend fun updateNote(note: Note)
    suspend fun deleteNote(note: Note)
    fun getAllNotes()
    fun getNoteById(id: Long)
    fun getNotesByFolderId(id: Long)
    fun searchNotes(query: String)
}