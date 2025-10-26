package com.example.notescompose.domain.model

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.notescompose.ui.theme.AquaNotes
import com.example.notescompose.ui.theme.GrayNotes
import com.example.notescompose.ui.theme.GreenNotes
import com.example.notescompose.ui.theme.PinkNotes
import com.example.notescompose.ui.theme.PurpleNotes
import com.example.notescompose.ui.theme.RedNotes
import com.example.notescompose.ui.theme.YellowNotes

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