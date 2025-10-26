package com.example.notescompose.dataClass

import kotlinx.serialization.Serializable

@Serializable
data class Search(
    val id: String = "search",
)