package com.example.introtocompose.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.introtocompose.model.Note
import com.example.introtocompose.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val repository: NoteRepository
) : ViewModel() {
    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()

    init {
        Log.d("NoteViewModel", "init")
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllNotes().distinctUntilChanged().collect { listOfNotes ->
                if (listOfNotes.isNullOrEmpty()) {
                    Log.d("Empty", ": Empty list")
                    _noteList.value = emptyList()
                } else {
                    _noteList.value = listOfNotes
                }
            }
        }
    }

    /*
    Add note to the list of notes.
    @param note: Note
     */
    fun addNote(note: Note) = viewModelScope.launch { repository.addNote(note) }

    /*
    Update note in the list of notes.
    @param note: Note
     */
    fun updateNote(note: Note) = viewModelScope.launch { repository.updateNote(note) }

    /*
    Remove given note from the list of notes.
    @param note: Note
     */
    fun removeNote(note: Note) = viewModelScope.launch { repository.deleteNote(note) }

    /*
    Get all notes from the list of notes.
    @return List<Note>
     */
    //fun getAllNotes(): List<Note> = viewModelScope.launch { repository.getAllNotes() }
}
