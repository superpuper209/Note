package com.example.notescompose.presentation.screens

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.notescompose.dataClass.AddNote
import com.example.notescompose.dialog.DialogBack
import com.example.notescompose.domain.model.Note
import com.example.notescompose.presentation.add_edit_note.AddEditNoteEvent
import com.example.notescompose.presentation.add_edit_note.AddEditNoteViewModel
import com.example.notescompose.presentation.add_edit_note.TransparentHintTextField
import com.example.notescompose.presentation.notes.NotesEvent
import com.example.notescompose.presentation.notes.components.NotesViewModel
import com.example.notescompose.ui.theme.Black2
import com.example.notescompose.ui.theme.TextColorGray
import kotlinx.coroutines.launch
import kotlin.system.exitProcess


@Composable
fun AddEditNoteScreen (
    addNote: AddNote,
    navController: NavController,
    noteColor: Int,
    viewModel: AddEditNoteViewModel = hiltViewModel(),
    context: Context,
    onDialogBack:  () -> Unit,
    onBackAddNote:  () -> Unit,
) {

    val snackbarHostState = remember { SnackbarHostState() }

    val titleState = viewModel.noteTitle.value
    val contentState = viewModel.noteContent.value

    val noteBackgroundAnimatable = remember{
        Animatable (
            Color(if(noteColor != -1) noteColor else viewModel.noteColor.value)
        )
    }

    val scope = rememberCoroutineScope()

    BackHandler(enabled = true) {

        val hasContent = contentState.text.isNotBlank()
        val hasTitle = titleState.text.isNotBlank()

        if (hasContent || hasTitle) {
            onDialogBack()
        } else {
            onBackAddNote()
        }

    }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collect { event ->
            when (event) {
                is AddEditNoteViewModel.UiEvent.ShowSnackbar -> {

                  Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()

                }
                is AddEditNoteViewModel.UiEvent.SaveNote -> {
                    navController.navigateUp()
                }
            }
        }
    }

    Scaffold(
        snackbarHost = {
        SnackbarHost(hostState = snackbarHostState)
    },
        ) { paddingValues ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(noteBackgroundAnimatable.value)
                .padding(paddingValues),
            verticalArrangement = Arrangement.SpaceBetween,

            ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(top = 30.dp, start = 16.dp, end = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Card (
                    modifier = Modifier
                        .height(43.dp)
                        .width(43.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Black2,
                        contentColor = Color.White
                    )
                ) {
                    IconButton (onClick = {
                        onDialogBack()
                    }) {
                        Icon (
                            imageVector = Icons.Filled.ArrowBackIosNew,
                            contentDescription = "back",
                        )
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(24.dp)
                ) {

                    Card(
                        modifier = Modifier
                            .height(43.dp)
                            .width(43.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Black2,
                            contentColor = Color.White
                        )
                    ) {
                        IconButton(onClick = {

                        val hasContent = contentState.text.isNotBlank()
                        val hasTitle = titleState.text.isNotBlank()

                        if (hasContent && hasTitle) {
                            viewModel.onEvent(AddEditNoteEvent.SaveNote)
                            onBackAddNote()
                        } else {
                            var messageContent = ""

                            if (!hasContent) messageContent = "Заметка не может быть пустой"
                            else if(!hasTitle) messageContent = "Заметка не может быть без названия"

                            scope.launch {
                                snackbarHostState.showSnackbar (
                                    message = messageContent,
                                    actionLabel = "Ок",
                                    duration = SnackbarDuration.Short
                                )
                            }
                        }

                        }) {
                            Icon(
                                imageVector = Icons.Filled.Save,
                                contentDescription = "save notes",
                            )
                        }
                    }
                }
            }

            Row(
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Note.noteColors.forEach{ color ->
                    val colorInt = color.toArgb()

                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .shadow(15.dp, CircleShape)
                            .clip(CircleShape)
                            .background(color)
                            .border(
                                width = 3.dp,
                                color = if (viewModel.noteColor.value == colorInt) {
                                    Color.Black
                                } else Color.Transparent,
                                shape = CircleShape
                            )
                            .clickable {
                                scope.launch {
                                   noteBackgroundAnimatable.animateTo(
                                       targetValue = Color(colorInt),
                                       animationSpec = tween(
                                           durationMillis = 500
                                       )
                                   )
                                }
                                viewModel.onEvent(AddEditNoteEvent.ChangeColor(colorInt))
                            }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            TransparentHintTextField(
                text = titleState.text,
                hint = titleState.hint,
                hintColor = TextColorGray,
                onFocusChange = {
                    viewModel.onEvent(AddEditNoteEvent.ChangeTitleFocus(it))
                },
                onValueChange = {
                    viewModel.onEvent(AddEditNoteEvent.EnteredTitle(it))
                },
                isHintVisible = titleState.isHintVisible,
                singleLine = true,

                textStyle = MaterialTheme.typography.headlineMedium,
            )

            Spacer(modifier = Modifier.height(16.dp))

            TransparentHintTextField(
                text = contentState.text,
                hint = contentState.hint,

                onFocusChange = {
                    viewModel.onEvent(AddEditNoteEvent.ChangeContentFocus(it))
                },
                onValueChange = {
                    viewModel.onEvent(AddEditNoteEvent.EnteredContent(it))
                },
                isHintVisible = contentState.isHintVisible,
                textStyle = MaterialTheme.typography.headlineSmall,

                modifier = Modifier.fillMaxHeight(),
                hintColor = TextColorGray
            )
        }
    }
}