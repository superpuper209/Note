package com.example.notescompose.app.navigation

import kotlinx.serialization.Serializable

sealed interface Routes {
    @Serializable
    data object Home : Routes

    @Serializable
    data class ScreenNote(
        val id: String = "screenNote",
    ) : Routes

    @Serializable
    data class Setting(
        val id: String = "setting",
    ) : Routes

    @Serializable
    data class AddNote(
        val id: String = "addNote",
    )
    @Serializable
    data class Search(
        val id: String = "search",
    )

}