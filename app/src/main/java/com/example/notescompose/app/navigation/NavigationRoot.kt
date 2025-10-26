package com.example.notescompose.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import com.example.notescompose.core.design_system.theme.GrayNotes
import com.example.notescompose.core.design_system.dialogs.DialogBack
import com.example.notescompose.core.design_system.dialogs.DialogInfo
import com.example.notescompose.core.design_system.dialogs.DialogPassword

import com.example.notescompose.presentation.screens.AddEditNoteScreen
import com.example.notescompose.presentation.screens.HomeScreen
import com.example.notescompose.presentation.screens.SearchNoteScreen
import com.example.notescompose.presentation.screens.SettingScreen
import com.example.notescompose.presentation.util.Screen

@Composable
fun NavigationRoot(navHostController: NavHostController) {

    val dialogState = remember {
        mutableStateOf(false)
    }

    if (dialogState.value) {
        DialogInfo(dialogState, onSubmit = {

        })
    }

    val dialogStateBack = remember {
        mutableStateOf(false)
    }

    if (dialogStateBack.value) {
        DialogBack(
            dialogStateBack,
            onSubmit = {

            },
            onBackAddNote = {
                navHostController.navigate(Routes.Home)
            },

            )
    }

    val dialogStatePassword = remember {
        mutableStateOf(false)
    }

    if (dialogStatePassword.value) {
        DialogPassword(
            dialogStatePassword, onSubmit = {

            }
        )
    }
    NavHost(
        navController = navHostController,
        startDestination = Routes.Home,
    ) {
        composable<Routes.Home> {
            HomeScreen(
                onClickInfo = {
                    dialogState.value = true
                },
                onSearchScreen = {
                    navHostController.navigate(Routes.Search)
                },
                navController = navHostController,

                onAddScreen = {
                    navHostController.navigate(Routes.AddNote)
                },
                onSettingScreen = {
                    navHostController.navigate(Routes.Setting)
                },
            )
        }
        composable(
            route = Screen.AddEditNoteScreen.route + "?noteId={noteId}&noteColor={noteColor}",

            arguments = listOf(
                navArgument("noteId") {
                    type = NavType.Companion.IntType
                    defaultValue = -1
                },
                navArgument("noteColor") {
                    type = NavType.Companion.IntType
                    defaultValue = -1
                }
            )
        ) { backStackEntry ->
            //val noteId = backStackEntry.arguments?.getInt("noteId") ?: -1
            val colorInt = backStackEntry.arguments?.getInt("noteColor")
            val safeColorInt = colorInt ?: -1
            val addNote = backStackEntry.toRoute<Routes.AddNote>()

            AddEditNoteScreen(
                addNote,
                navController = navHostController,
                noteColor = safeColorInt,
                context = LocalContext.current,
                onDialogBack = {
                    dialogStateBack.value = true
                },
                onBackAddNote = {
                    navHostController.navigate(Routes.Home)
                },
            )
        }
        composable<Routes.Search> { backStackEntry ->
            val search = backStackEntry.toRoute<Routes.Search>()
            SearchNoteScreen(
                search,
                navController = navHostController
            )

        }
        composable<Routes.Setting> { backStackEntry ->
            val setting = backStackEntry.toRoute<Routes.Setting>()
            SettingScreen(
                setting,
                navController = navHostController,
                onBackSetting = {
                    navHostController.navigate(Routes.Home)
                },
                onDialogPassword = {
                    dialogStatePassword.value = true
                }
            )
        }

        composable<Routes.AddNote> { backStackEntry ->
            val addNote = backStackEntry.toRoute<Routes.AddNote>()
            AddEditNoteScreen(
                addNote,

                navController = navHostController,
                noteColor = GrayNotes.toArgb(),
                context = LocalContext.current,
                onDialogBack = {
                    dialogStateBack.value = true
                },
                onBackAddNote = {
                    navHostController.navigate(Routes.Home)
                },
            )
        }
    }
}