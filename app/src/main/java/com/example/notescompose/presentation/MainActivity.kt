package com.example.notescompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import com.example.notescompose.System.SystemBarsColor
import com.example.notescompose.dataClass.AddNote
import com.example.notescompose.dataClass.Search
import com.example.notescompose.dataClass.Setting
import com.example.notescompose.dialog.DialogBack
import com.example.notescompose.dialog.DialogInfo
import com.example.notescompose.dialog.DialogPassword
import com.example.notescompose.`object`.Home
import com.example.notescompose.presentation.screens.AddEditNoteScreen
import com.example.notescompose.presentation.screens.HomeScreen
import com.example.notescompose.presentation.screens.SearchNoteScreen
import com.example.notescompose.presentation.screens.SettingScreen
import com.example.notescompose.presentation.util.Screen
import com.example.notescompose.ui.theme.GrayNotes
import com.example.notescompose.ui.theme.NotesComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity() : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            NotesComposeTheme {
                SystemBarsColor(
                    color = MaterialTheme.colorScheme.surface
                )

                val navController = rememberNavController()
                ///////////////
                val dialogState = remember {
                    mutableStateOf(false)
                }

                if (dialogState.value) {
                    DialogInfo(dialogState, onSubmit = {

                    })
                }
                //////////////////
                val dialogStateBack = remember {
                    mutableStateOf(false)
                }

                if (dialogStateBack.value) {
                    DialogBack(
                        dialogStateBack, onSubmit = {

                        },
                        onBackAddNote = {
                            navController.navigate(Home)
                        },

                    )
                }
                ////////////////////
                val dialogStatePassword = remember {
                    mutableStateOf(false)
                }

                if (dialogStatePassword.value) {
                    DialogPassword(
                        dialogStatePassword, onSubmit = {

                        }
                    )
                }
                //////////////
                Scaffold(modifier = Modifier.Companion.fillMaxSize()) { innerPadding ->

                    NavHost(
                        navController = navController,
                        startDestination = Home,
                        modifier = Modifier.Companion.padding(innerPadding)
                    ) {
                        composable<Home> {
                            HomeScreen(
                                onClickInfo = {
                                    dialogState.value = true
                                },
                                onSearchScreen = {
                                    navController.navigate(Search())
                                },
                                navController = navController,

                                onAddScreen = {
                                    navController.navigate(AddNote())
                                },
                                onSettingScreen = {
                                    navController.navigate(Setting())
                                },
                            )
                        }

                        composable(
                            route = Screen.AddEditNoteScreen.route + "?noteId={noteId}&noteColor={noteColor}",

                            arguments = listOf(
                                navArgument("noteId") {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                                navArgument("noteColor") {
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            )
                        ) { backStackEntry ->
                            //val noteId = backStackEntry.arguments?.getInt("noteId") ?: -1
                            val colorInt = backStackEntry.arguments?.getInt("noteColor")
                            val safeColorInt = colorInt ?: -1
                            val addNote = backStackEntry.toRoute<AddNote>()

                            AddEditNoteScreen(
                                addNote,
                                navController = navController,
                                noteColor = safeColorInt,
                                context = LocalContext.current,
                                onDialogBack = {
                                    dialogStateBack.value = true
                                },
                                onBackAddNote = {
                                    navController.navigate(Home)
                                },
                            )
                        }
                        composable<Search> { backStackEntry ->
                            val search = backStackEntry.toRoute<Search>()
                            SearchNoteScreen(
                                search,
                                navController = navController
                            )

                        }
                        composable<Setting> { backStackEntry ->
                            val setting = backStackEntry.toRoute<Setting>()
                            SettingScreen(
                                setting,
                                navController = navController,
                                onBackSetting = {
                                    navController.navigate(Home)
                                },
                                onDialogPassword = {
                                    dialogStatePassword.value = true
                                }
                            )
                        }

                        composable<AddNote> { backStackEntry ->
                            val addNote = backStackEntry.toRoute<AddNote>()
                            AddEditNoteScreen(
                                addNote,

                                navController = navController,
                                noteColor = GrayNotes.toArgb(),
                                context = LocalContext.current,
                                onDialogBack = {
                                    dialogStateBack.value = true
                                },
                                onBackAddNote = {
                                    navController.navigate(Home)
                                },
                            )
                        }
                    }
                }
            }
        }
    }
}