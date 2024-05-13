package dev.luizleal.mynotes.repository

import dev.luizleal.mynotes.database.NoteDatabase
import dev.luizleal.mynotes.model.Note

class NoteRepository(private val db: NoteDatabase) {
    private val noteDb = db.getNoteDao()

    suspend fun insertNote(note: Note) = noteDb.insertNote(note)

    suspend fun updateNote(note: Note) = noteDb.updateNote(note)

    suspend fun deleteNote(note: Note) = noteDb.deleteNote(note)

    fun getAllNotes() = noteDb.getAllNotes()

    fun searchNote(query: String?) = noteDb.searchNoteQuery(query)
}