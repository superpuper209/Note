package com.example.notescompose.presentation.notes

import com.example.notescompose.domain.model.Note
import com.example.notescompose.domain.util.NoteOrder
import com.example.notescompose.domain.util.OrderType

data class NotesState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false,


    val searchQuery: String = "", // для поиска
)
