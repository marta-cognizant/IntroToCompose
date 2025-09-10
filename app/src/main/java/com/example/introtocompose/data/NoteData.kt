package com.example.introtocompose.data

import com.example.introtocompose.model.Note

class NotesDataSource {
    fun loadNotes(): List<Note> {
        return listOf(
            Note(title = "TODO", description = "Add code to GitHub."),
            Note(title = "Groceries", description = "milk, butter, bread"),
            Note(title = "Movie afternoon", description = "Watch a movie with my husband"),
            Note(title = "Test", description = "test 1234"),
            Note(
                title = "Lorem Ipsum",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
            )
        )
    }
}