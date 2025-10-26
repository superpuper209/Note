package com.example.notescompose.domain.use_case

import com.example.notescompose.domain.model.Note
import com.example.notescompose.feature_note.data.repository.NoteRepository

class GetNote(
    private val repository: NoteRepository,
) {

    suspend operator fun invoke(id: Int): Note? {
        return repository.getNoteById(id)
    }
}