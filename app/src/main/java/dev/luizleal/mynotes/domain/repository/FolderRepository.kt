package dev.luizleal.mynotes.domain.repository

import dev.luizleal.mynotes.domain.model.Folder
import kotlinx.coroutines.flow.Flow

interface FolderRepository {

    suspend fun insertFolder(folder: Folder)
    suspend fun updateFolder(folder: Folder)
    suspend fun deleteFolderFolder(folder: Folder)
    fun getAllFolders(): Flow<List<Folder?>>
}