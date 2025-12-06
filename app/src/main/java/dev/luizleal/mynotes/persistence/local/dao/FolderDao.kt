package dev.luizleal.mynotes.persistence.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import dev.luizleal.mynotes.persistence.entity.FolderEntity
import dev.luizleal.mynotes.persistence.entity.NoteEntity
import dev.luizleal.mynotes.util.Constants
import kotlinx.coroutines.flow.Flow

@Dao
interface FolderDao {

    @Insert
    suspend fun insertFolder(folder: FolderEntity)

    @Update
    suspend fun updateFolder(folder: FolderEntity)

    @Delete
    suspend fun deleteFolder(folder: FolderEntity)

    @Query("SELECT * FROM ${Constants.FOLDER_TABLE}")
    fun getAllFolders(): Flow<List<FolderEntity>>

    @Query(
        "SELECT * FROM ${Constants.FOLDER_TABLE} WHERE name LIKE '%' || :query || '%'"
    )
    fun searchFolders(query: String): Flow<List<FolderEntity>>
}