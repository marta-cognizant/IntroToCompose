package com.example.introtocompose.screen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.introtocompose.data.NotesDataSource
import com.example.introtocompose.model.Note

class NoteViewModel: ViewModel() {
    private var noteList = mutableStateListOf<Note>()

    init {
        noteList.addAll(NotesDataSource().loadNotes())
    }

    /*
    Add note to the list of notes.
    @param note: Note
     */
    fun addNote(note: Note) {
        noteList.add(note)
    }

    /*
    Remove given note from the list of notes.
    @param note: Note
     */
    fun removeNote(note: Note) {
        noteList.remove(note)
    }

    /*
    Get all notes from the list of notes.
    @return List<Note>
     */
    fun getAllNotes(): List<Note> {
        return noteList
    }
}