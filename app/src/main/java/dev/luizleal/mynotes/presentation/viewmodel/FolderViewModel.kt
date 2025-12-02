package dev.luizleal.mynotes.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.luizleal.mynotes.domain.model.Folder
import dev.luizleal.mynotes.domain.repository.FolderRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class FolderViewModel @Inject constructor(
    private val repository: FolderRepository
) : ViewModel() {

    private val _folderListState = MutableStateFlow(FolderState<List<Folder>>())
    val folderListState = _folderListState.asStateFlow()

    init {
        getAllFolders()
    }

    fun getAllFolders() = viewModelScope.launch {
        _folderListState.update { state ->
            state.copy(isLoading = true)
        }

        try {
            repository.getAllFolders().collect { folders ->
                _folderListState.update { state ->
                    state.copy(
                        data = folders,
                        isLoading = false
                    )
                }
            }
        } catch (e: Exception) {
            Log.e("FolderViewModel", "Error getting folders: $e")
            _folderListState.update { state ->
                state.copy(error = e.message, isLoading = false)
            }

            return@launch
        }
    }

    fun insertFolder(folder: Folder) = viewModelScope.launch {
        _folderListState.update { state ->
            state.copy(isLoading = true)
        }

        try {
            repository.insertFolder(folder)
        } catch (e: Exception) {
            Log.e("FolderViewModel", "Error inserting folder: $e")
            _folderListState.update { state ->
                state.copy(error = e.message, isLoading = false)
            }

            return@launch
        }
    }

    fun updateFolder(folder: Folder) = viewModelScope.launch {
        _folderListState.update { state ->
            state.copy(isLoading = true)
        }

        try {
            repository.updateFolder(folder)
        } catch (e: Exception) {
            Log.e("FolderViewModel", "Error updating folder: $e")
            _folderListState.update { state ->
                state.copy(error = e.message, isLoading = false)
            }

            return@launch
        }
    }

    fun deleteFolder(folder: Folder) = viewModelScope.launch {
        _folderListState.update { state ->
            state.copy(isLoading = true)
        }

        try {
            repository.deleteFolder(folder)
        } catch (e: Exception) {
            Log.e("FolderViewModel", "Error deleting folder: $e")
            _folderListState.update { state ->
                state.copy(error = e.message, isLoading = false)
            }

            return@launch
        }
    }

}

data class FolderState<T>(
    val isLoading: Boolean = false,
    val data: T? = null,
    val error: String? = null
)