package com.example.notescompose.presentation.add_edit_note

import com.example.notescompose.domain.model.Note

data class HomeState(
    val isLoading: Boolean = false,
    val notes: List<Note> = emptyList()
)