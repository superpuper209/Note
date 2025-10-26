package com.example.notescompose.domain.use_case

import com.example.notescompose.domain.model.Note
import com.example.notescompose.feature_note.data.repository.NoteRepository

class DeleteNote(
    private val repository: NoteRepository

) {

    suspend operator fun invoke(note: Note) {
        repository.deleteNote(note)
    }
}