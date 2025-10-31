package dev.luizleal.mynotes.domain.repository

import dev.luizleal.mynotes.persistence.entity.FolderEntity

interface FolderRepository {

    suspend fun insertFolder(folder: FolderEntity)
    suspend fun updateFolder(folder: FolderEntity)
    suspend fun deleteFolderFolder(folder: FolderEntity)
    fun getAllFolders(folder: FolderEntity)
}