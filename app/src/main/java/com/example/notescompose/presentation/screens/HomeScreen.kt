package com.example.notescompose.presentation.screens

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.notescompose.presentation.notes.NotesEvent
import com.example.notescompose.presentation.notes.components.NotesItem
import com.example.notescompose.presentation.notes.components.NotesViewModel
import com.example.notescompose.presentation.notes.components.OrderSection
import com.example.notescompose.presentation.util.Screen
import com.example.notescompose.ui.theme.Black2
import com.example.notescompose.ui.theme.Black3
import com.example.notescompose.ui.theme.BlackBg
import com.example.notescompose.ui.theme.GrayBgAddNotes
import com.example.notescompose.ui.theme.GrayTopAppBar
import com.example.notescompose.ui.theme.GreenNotes
import com.example.notescompose.ui.theme.RedNotes
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onClickInfo: () -> Unit,
    onSearchScreen: () -> Unit,
    onAddScreen: () -> Unit,
    onSettingScreen: () -> Unit,
    navController: NavController,
    viewModel: NotesViewModel = hiltViewModel()
)  {


    val state = viewModel.state.value
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {
            ModalDrawerSheet(modifier = Modifier.fillMaxHeight().width(270.dp),

                drawerContainerColor = Black2
            ) {
                Spacer(Modifier.height(16.dp))
                Text("Меню", modifier = Modifier.padding(16.dp), color = Color.White, style = MaterialTheme.typography.headlineMedium)

                NavigationDrawerItem(
                    label = { Text("Настройки", color = Color.White) },
                    selected = false,
                    onClick = {
                        scope.launch { drawerState.close() }
                        onSettingScreen()
                    },
                    icon = { Icon(Icons.Filled.Settings, contentDescription = "Настройки", tint = Color.White) },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                    colors = NavigationDrawerItemDefaults.colors(
                        unselectedContainerColor = Color.Transparent
                    )
                )

                NavigationDrawerItem(
                    label = { Text("Информация", color = Color.White) },
                    selected = false,
                    onClick = {
                        scope.launch { drawerState.close() }
                        onClickInfo()
                    },
                    icon = { Icon(Icons.Filled.Info, contentDescription = "Информация", tint = Color.White) },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                    colors = NavigationDrawerItemDefaults.colors(
                        unselectedContainerColor = Color.Transparent
                    )
                )

                NavigationDrawerItem(
                    label = { Text("Поиск", color = Color.White) },
                    selected = false,
                    onClick = {
                        scope.launch { drawerState.close() }
                        onSearchScreen()
                    },
                    icon = { Icon(Icons.Filled.Search, contentDescription = "Поиск", tint = Color.White) },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                    colors = NavigationDrawerItemDefaults.colors(
                        unselectedContainerColor = Color.Transparent
                    )
                )

            }
        }
    ) {


        Scaffold(
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
            },
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = GrayTopAppBar,
                        navigationIconContentColor = Color.White,
                        actionIconContentColor = Color.White
                    ),
                    title = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(start = 10.dp)
                        ) {
                            Text(
                                text = "Заметки",
                                color = Color.White,
                                style = MaterialTheme.typography.headlineMedium,
                                fontSize = 34.sp,
                            )
                        }
                    },
                    navigationIcon = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(start = 16.dp)
                        ) {
                            Card(
                                modifier = Modifier
                                    .height(43.dp)
                                    .width(43.dp),
                                //.padding(start = 16.dp),
                                shape = RoundedCornerShape(12.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = Black2,
                                    contentColor = Color.White
                                )
                            ) {
                                IconButton(
                                    onClick = {
                                         scope.launch {
                                             drawerState.open()
                                         }
                                    }
                                ) {
                                    Icon(imageVector = Icons.Filled.Menu,
                                        contentDescription = "menu")
                                }
                            }
                        }
                    },
                    actions = {

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.End),
                            modifier = Modifier
                                .padding(end = 16.dp)
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
                                IconButton(
                                    onClick = {
                                        viewModel.OnEvent(NotesEvent.ToggleOrderSection)
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Sort,
                                        contentDescription = "sort"
                                    )
                                }
                            }
                        }
                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        onAddScreen.invoke()
                    },
                    contentColor = Color.White,
                    containerColor = GrayBgAddNotes
                ) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = "add")
                }
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(BlackBg)
                    .padding(paddingValues),
                verticalArrangement = Arrangement.SpaceBetween,

                ) {


                AnimatedVisibility(
                    visible = state.isOrderSectionVisible,
                    enter = fadeIn() + slideInVertically(),
                    exit = fadeOut() + slideOutVertically()
                ) {
                    OrderSection(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        noteOrder = state.noteOrder,
                        onOrderChange = {
                            viewModel.OnEvent(NotesEvent.Order(it))
                        }

                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

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
                    }
                }
            }
        }
    }
}

// отановился на том что сделал Drawer and TopAppBar и перекрасил верхнюю и нижнюю панель
// надо разобраться с анимацией и переходить к паролю