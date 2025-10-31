package dev.luizleal.mynotes.domain.repository

import dev.luizleal.mynotes.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    suspend fun insertNote(note: Note)
    suspend fun updateNote(note: Note)
    suspend fun deleteNote(note: Note)
    fun getAllNotes(): Flow<List<Note?>>
    fun getNoteById(id: Long): Flow<Note?>
    fun getNotesByFolderId(id: Long): Flow<List<Note?>>
    fun searchNotes(query: String): Flow<List<Note?>>
}