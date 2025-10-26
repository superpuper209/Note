package com.example.notescompose.dataClass

import kotlinx.serialization.Serializable

@Serializable
data class AddNote(
    val id: String = "addNote",
)
