package com.example.notescompose.presentation.notes

import com.example.notescompose.domain.model.Note
import com.example.notescompose.domain.util.NoteOrder

sealed class NotesEvent {

    data class Order(val noteOrder: NoteOrder): NotesEvent()
    data class DeleteNote(val note: Note): NotesEvent()
    object RestoreNote: NotesEvent()
    object ToggleOrderSection: NotesEvent()

    data class OnSearchTextChange(val text: String) : NotesEvent() // для поиска

}