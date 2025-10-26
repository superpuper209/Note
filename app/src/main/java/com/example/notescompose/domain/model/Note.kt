package com.example.notescompose.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.notescompose.core.design_system.theme.AquaNotes
import com.example.notescompose.core.design_system.theme.GrayNotes
import com.example.notescompose.core.design_system.theme.GreenNotes
import com.example.notescompose.core.design_system.theme.PinkNotes
import com.example.notescompose.core.design_system.theme.PurpleNotes
import com.example.notescompose.core.design_system.theme.YellowNotes

@Entity
data class Note(
    val timestamp: Long,
    val color: Int,
    val title: String,
    val content: String,

     @PrimaryKey(autoGenerate = true) val id: Int = 0
) {
    companion object {
        val noteColors = listOf(PinkNotes, YellowNotes, AquaNotes, GreenNotes,  PurpleNotes, GrayNotes)
    }
}

class InvalidNoteException(message: String): Exception(message)