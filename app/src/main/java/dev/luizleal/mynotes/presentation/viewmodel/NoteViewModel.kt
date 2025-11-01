package dev.luizleal.mynotes.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.luizleal.mynotes.domain.model.Note
import dev.luizleal.mynotes.domain.repository.NoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val repository: NoteRepository
) : ViewModel() {

    private val _noteListState = MutableStateFlow(NoteState<List<Note>>())
    val noteListState = _noteListState.asStateFlow()

    private val _noteState = MutableStateFlow(NoteState<Note>())
    val noteState = _noteState.asStateFlow()

    init {
        getAllNotes()
    }

    fun getAllNotes() = viewModelScope.launch {
        _noteState.update { state ->
            state.copy(isLoading = true)
        }

        try {
            repository.getAllNotes().collect { notes ->
                _noteListState.update { state ->
                    state.copy(
                        data = notes,
                        isLoading = false
                    )
                }
            }
        } catch (e: Exception) {
            Log.e("NoteViewModel", "Error getting notes: $e")
            _noteListState.update { state ->
                state.copy(error = e.message, isLoading = false)
            }

            return@launch
        }
    }

    fun getNoteById(id: Long) = viewModelScope.launch {
        _noteState.update { state ->
            state.copy(isLoading = true)
        }

        try {
            repository.getNoteById(id).collect { note ->
                _noteState.update { state ->
                    state.copy(
                        data = note,
                        isLoading = false
                    )
                }
            }
        } catch (e: Exception) {
            Log.e("NoteViewModel", "Error getting note: $e")
            _noteListState.update { state ->
                state.copy(error = e.message, isLoading = false)
            }

            return@launch
        }
    }

    fun getNotesByFolderId(id: Long) = viewModelScope.launch {
        _noteListState.update { state ->
            state.copy(isLoading = true)
        }

        try {
            repository.getNotesByFolderId(id).collect { notes ->
                _noteListState.update { state ->
                    state.copy(
                        data = notes,
                        isLoading = false
                    )
                }
            }
        } catch (e: Exception) {
            Log.e("NoteViewModel", "Error getting notes: $e")
            _noteListState.update { state ->
                state.copy(error = e.message, isLoading = false)
            }

            return@launch
        }
    }

    fun searchNotes(query: String) = viewModelScope.launch {
        _noteListState.update { state ->
            state.copy(isLoading = true)
        }

        try {
            repository.searchNotes(query).collect { notes ->
                _noteListState.update { state ->
                    state.copy(
                        data = notes,
                        isLoading = false
                    )
                }
            }
        } catch (e: Exception) {
            Log.e("NoteViewModel", "Error searching notes: $e")
            _noteListState.update { state ->
                state.copy(error = e.message, isLoading = false)
            }

            return@launch
        }
    }

    fun insertNote(title: String, content: String) = viewModelScope.launch {
        _noteListState.update { state ->
            state.copy(isLoading = true)
        }

        try {
            val note = Note(
                title = title,
                content = content
            )
            repository.insertNote(note)
        } catch (e: Exception) {
            Log.e("NoteViewModel", "Error inserting note: $e")
            _noteListState.update { state ->
                state.copy(error = e.message, isLoading = false)
            }

            return@launch
        }
    }

    fun updateNote(note: Note) = viewModelScope.launch {
        _noteListState.update { state ->
            state.copy(isLoading = true)
        }

        try {
            repository.updateNote(note)
        } catch (e: Exception) {
            Log.e("NoteViewModel", "Error updating note: $e")
            _noteListState.update { state ->
                state.copy(error = e.message, isLoading = false)
            }

            return@launch
        }
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        _noteListState.update { state ->
            state.copy(isLoading = true)
        }

        try {
            repository.deleteNote(note)
        } catch (e: Exception) {
            Log.e("NoteViewModel", "Error deleting note: $e")
            _noteState.update { state ->
                state.copy(error = e.message, isLoading = false)
            }

            return@launch
        }
    }

}

data class NoteState<T>(
    val isLoading: Boolean = false,
    val data: T? = null,
    val error: String? = null
)