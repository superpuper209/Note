package com.example.notescompose.domain.use_case

import com.example.notescompose.domain.model.InvalidNoteException
import com.example.notescompose.domain.model.Note
import com.example.notescompose.feature_note.data.repository.NoteRepository

class AddNote(
    private val noteRepository: NoteRepository

) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note){
        if(note.title.isBlank()){
            throw InvalidNoteException("Заголовок заметки не может быть пустым.")
        }
        if(note.content.isBlank()){
            throw InvalidNoteException("Содержимое заметки не может быть пустым.")
        }
        noteRepository.insertNote(note)
    }
}