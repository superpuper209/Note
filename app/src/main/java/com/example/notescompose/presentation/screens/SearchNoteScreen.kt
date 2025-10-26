package com.example.notescompose.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.notescompose.dataClass.Search
import com.example.notescompose.presentation.notes.NotesEvent
import com.example.notescompose.presentation.notes.components.NotesItem
import com.example.notescompose.presentation.notes.components.NotesViewModel
import com.example.notescompose.presentation.util.Screen
import com.example.notescompose.core.design_system.theme.Black2
import com.example.notescompose.core.design_system.theme.BlackBg
import com.example.notescompose.core.design_system.theme.TextColorGray
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchNoteScreen(
    search: Search,
    viewModel: NotesViewModel = hiltViewModel(),
    navController: NavController,
) {

    val state = viewModel.state.value
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val searchText = remember {
        mutableStateOf("")
    }
    val isActive = remember {
        mutableStateOf(false)
    }
    val mainList = remember {
        mutableStateOf(Utils.originUserList)
    }

    Column(
        modifier = Modifier.fillMaxSize()
            .background(BlackBg),
    ) {

        SearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),


            query = searchText.value,
            onQueryChange = {text ->
                searchText.value = text
                mainList.value = Utils.search(text)
            },
            onSearch = {text ->

            },
            colors = SearchBarDefaults.colors(
                containerColor = Black2,

                inputFieldColors = TextFieldDefaults.colors(

                    unfocusedTextColor = TextColorGray,
                    focusedTextColor = TextColorGray,
                    cursorColor = TextColorGray,
                )
            ),

            placeholder = { // текст по умолчанию
                Text(text = "Поиск по ключевому слову...", color = TextColorGray)

            },
            active = isActive.value,
            // вот этот параметр показывать работу в { } SearchBar,
            // если он false то все что в скобках работать не будет, если treu то будет
            onActiveChange = {
                isActive.value = it
            },

            trailingIcon = {
                IconButton(
                    onClick = {
                        if (searchText.value.isNotEmpty()) {
                            searchText.value = ""
                            mainList.value = emptyList()
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Clear,
                        contentDescription = "поиск",
                        tint = TextColorGray
                    )
                }
            },



        ) {
            /*LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(mainList.value) { item ->
                    Box(
                        modifier = Modifier
                            .clickable{
                                mainList.value = Utils.search(item) // это уже когда в поиске нажимаешь оно выбирается
                                isActive.value = false
                            }
                            .fillMaxWidth()
                            .padding(10.dp),
                        contentAlignment = Alignment.CenterStart // строка которая ставит объекты по середине слева экрана
                    ) {
                        Text(text = item)
                    }
                }
            }*/


        }
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(state.notes) { note ->
                NotesItem(
                    note = note,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {

                            navController.navigate(
                                Screen.AddEditNoteScreen.route +
                                        "?noteId=${note.id}&noteColor=${note.color}"
                            )
                        }
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    onDeleteClick = {
                        viewModel.OnEvent(NotesEvent.DeleteNote(note))
                        scope.launch {
                            val result = snackbarHostState.showSnackbar(
                                message = "Заметка удалена",
                                actionLabel = "Отмена"
                            )
                            if (result == SnackbarResult.ActionPerformed) {
                                viewModel.OnEvent(NotesEvent.RestoreNote)
                            }
                        }
                    }
                )
                Spacer(modifier = Modifier.width(16.dp))
            }
        }


    }

}

object Utils {
    val originUserList = listOf("")

    fun search(text: String): List<String> {
        return originUserList.filter {
            it.lowercase().startsWith(text)  // строка корая делает фильтр по тексту
        }
    }
}

