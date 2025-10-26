package com.example.notescompose.System

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.example.notescompose.ui.theme.BlackBg
import com.example.notescompose.ui.theme.GrayTopAppBar

@Composable
fun SystemBarsColor(
    color: Color,
) {
    val view = LocalView.current
    val context = LocalContext.current
    val window = (context as? Activity)?.window

    if (window == null) return

    DisposableEffect(view, window, color) {
        val insetsController = WindowCompat.getInsetsController(window, view)


        val originalStatusBarColor = window.statusBarColor
        val originalNavigationBarColor = window.navigationBarColor
        val originalIsLightStatusBar = insetsController.isAppearanceLightStatusBars
        val originalIsLightNavigationBar = insetsController.isAppearanceLightNavigationBars


        window.statusBarColor = GrayTopAppBar.toArgb()
        window.navigationBarColor = BlackBg.toArgb()


       onDispose {
           window.statusBarColor = originalStatusBarColor
           window.navigationBarColor = originalNavigationBarColor
           insetsController.isAppearanceLightStatusBars = originalIsLightStatusBar
           insetsController.isAppearanceLightNavigationBars = originalIsLightNavigationBar
       }
    }
}