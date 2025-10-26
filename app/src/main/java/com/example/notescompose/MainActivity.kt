package com.example.notescompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.example.notescompose.app.navigation.NavigationRoot
import com.example.notescompose.core.design_system.system_bar.SystemBarsColor
import com.example.notescompose.core.design_system.theme.NotesComposeTheme
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

                NavigationRoot(navController)
            }
        }
    }
}