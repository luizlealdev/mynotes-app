package dev.luizleal.mynotes.persistence.repository

import dev.luizleal.mynotes.domain.model.Folder
import dev.luizleal.mynotes.domain.repository.FolderRepository
import dev.luizleal.mynotes.persistence.local.MyNotesDatabase
import dev.luizleal.mynotes.persistence.mapper.toEntity
import dev.luizleal.mynotes.persistence.mapper.toModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FolderRepositoryImpl(
    database: MyNotesDatabase
) : FolderRepository {

    val folderDao = database.folderDao()


    override suspend fun insertFolder(folder: Folder) {
        return folderDao.insertFolder(folder.toEntity())
    }

    override suspend fun updateFolder(folder: Folder) {
        return folderDao.updateFolder(folder.toEntity())
    }

    override suspend fun deleteFolder(folder: Folder) {
        return folderDao.deleteFolder(folder.toEntity())
    }

    override fun getAllFolders(): Flow<List<Folder>> {
        return folderDao.getAllFolders().map { folders ->
            folders.map {
                it.toModel()
            }
        }
    }
}
