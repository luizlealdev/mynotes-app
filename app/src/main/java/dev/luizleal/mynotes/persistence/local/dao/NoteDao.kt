package dev.luizleal.mynotes.persistence.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import dev.luizleal.mynotes.persistence.entity.NoteEntity
import dev.luizleal.mynotes.util.Constants
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert
    suspend fun insertNote(note: NoteEntity)

    @Update
    suspend fun updateNote(note: NoteEntity)

    @Delete
    suspend fun deleteNote(note: NoteEntity)

    @Query("SELECT * FROM ${Constants.NOTE_TABLE}")
    fun getAllNotes(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM ${Constants.NOTE_TABLE} WHERE id = :id")
    fun getNoteById(id: Long): Flow<NoteEntity?>

    @Query("SELECT * FROM ${Constants.NOTE_TABLE} WHERE folder_id = :id")
    fun getNotesByFolderID(id: Long): Flow<List<NoteEntity>>

    @Query(
        "SELECT * FROM ${Constants.NOTE_TABLE} WHERE title LIKE '%' || :query || '%' OR content LIKE '%' || :query || '%'"
    )
    fun searchNotes(query: String): Flow<List<NoteEntity>>
}