package com.example.notescompose.dataClass

import kotlinx.serialization.Serializable


@Serializable
data class Setting(
    val id: String = "setting",
)