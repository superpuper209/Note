package com.example.notescompose.presentation.add_edit_note

import androidx.compose.ui.graphics.Color
import com.example.notescompose.ui.theme.TextColorGray

data class NoteTextFieldState(
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true,

)
