package dev.luizleal.mynotes.domain.repository

import dev.luizleal.mynotes.persistence.entity.NoteEntity

interface NoteRepository {

    suspend fun insertNote(note: NoteEntity)
    suspend fun updateNote(note: NoteEntity)
    suspend fun deleteNote(note: NoteEntity)
    fun getAllNotes()
    fun getNoteById(id: Long)
    fun getNotesByFolderId(id: Long)
    fun searchNotes(query: String)
}