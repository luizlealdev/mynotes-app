package dev.luizleal.mynotes.persistence.local.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import dev.luizleal.mynotes.persistence.entity.FolderEntity
import dev.luizleal.mynotes.persistence.entity.NoteEntity
import dev.luizleal.mynotes.util.Constants

interface FolderDao {

    @Insert
    suspend fun insertFolder(folder: FolderEntity)

    @Update
    suspend fun updateFolder(folder: FolderEntity)

    @Delete
    suspend fun deleteFolder(folder: FolderEntity)

    @Query("SELECT * FROM ${Constants.FOLDER_TABLE}")
    fun getAllFolders(): List<FolderEntity?>
}