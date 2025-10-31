package dev.luizleal.mynotes.persistence.repository

import dev.luizleal.mynotes.domain.model.Note
import dev.luizleal.mynotes.domain.repository.NoteRepository
import dev.luizleal.mynotes.persistence.local.MyNotesDatabase
import dev.luizleal.mynotes.persistence.mapper.toEntity
import dev.luizleal.mynotes.persistence.mapper.toModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NoteRepositoryImpl(
    database: MyNotesDatabase
) : NoteRepository {

    val noteDao = database.noteDao()

    override suspend fun insertNote(note: Note) {
        return noteDao.insertNote(note.toEntity())
    }

    override suspend fun updateNote(note: Note) {
        return noteDao.updateNote(note.toEntity())
    }

    override suspend fun deleteNote(note: Note) {
        return noteDao.deleteNote(note.toEntity())
    }

    override fun getAllNotes(): Flow<List<Note>> {
        return noteDao.getAllNotes().map { notes ->
            notes.map {
                it.toModel()
            }
        }
    }

    override fun getNoteById(id: Long): Flow<Note?> {
        return noteDao.getNoteById(id).map {notes ->
            notes?.toModel()
        }
    }

    override fun getNotesByFolderId(id: Long): Flow<List<Note>> {
        return noteDao.getNotesByFolderID(id).map { notes ->
            notes.map {
                it.toModel()
            }
        }
    }

    override fun searchNotes(query: String): Flow<List<Note>> {
        return noteDao.searchNotes(query).map { notes ->
            notes.map {
                it.toModel()
            }
        }
    }
}