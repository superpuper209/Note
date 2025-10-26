package com.example.notescompose.presentation.screens

import android.graphics.Paint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.notescompose.dataClass.Setting
import com.example.notescompose.presentation.notes.NotesEvent
import com.example.notescompose.ui.theme.Black2
import com.example.notescompose.ui.theme.BlackBg
import com.example.notescompose.ui.theme.GrayTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(
    setting: Setting,
    navController: NavController,
    onBackSetting:  () -> Unit,
    onDialogPassword:  () -> Unit,
) {


    Scaffold(
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
                           text = "Настройки",
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
                           shape = RoundedCornerShape(12.dp),
                           colors = CardDefaults.cardColors(
                               containerColor = Black2,
                               contentColor = Color.White
                           )
                       ) {
                           IconButton (onClick = {
                               onBackSetting()
                           }) {
                               Icon (
                                   imageVector = Icons.Filled.ArrowBackIosNew,
                                   contentDescription = "back",
                               )
                           }
                       }
                   }
               }

           )
       }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BlackBg)
                .padding(paddingValues),

        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
            ) {
                Card (
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(0.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = BlackBg,
                    ),
                ) {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            onDialogPassword()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = BlackBg,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(0.dp),
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Пароль",
                                color = Color.White,
                                fontSize = 15.sp,
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
            ) {
                Card (
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(0.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = BlackBg,
                    ),
                ) {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            onDialogPassword()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = BlackBg,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(0.dp),
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Пароль",
                                color = Color.White,
                                fontSize = 15.sp,
                            )
                        }
                    }
                }
            }
        }
    }
}