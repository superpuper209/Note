package com.example.notescompose.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notescompose.ui.theme.Black2
import com.example.notescompose.ui.theme.BlackBg
import com.example.notescompose.ui.theme.GreenNotes
import com.example.notescompose.ui.theme.TextColorGray

@Composable
fun DialogInfo(dialogStateBack: MutableState<Boolean>, onSubmit: (String) -> Unit) {

    val dialogText = remember {
        mutableStateOf("")
    }
    AlertDialog(onDismissRequest = {
        dialogStateBack.value = false
    },
        containerColor = BlackBg,
        confirmButton = {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 70.dp, end = 70.dp),
                horizontalArrangement = Arrangement.Center

            ) {
                Card(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp),

                    ) {
                    TextButton(
                        onClick = {
                            onSubmit(dialogText.value)
                            dialogStateBack.value = false
                        },
                        modifier = Modifier.background(GreenNotes).fillMaxWidth()
                    ) {

                        Text(
                            text = "Ок", color = Color.Black, fontSize = 16.sp,

                        )
                    }
                }
            }
        },

        title = {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Это приложение заметок где можно создавать, редактировать, удалять и менять цвет заметки.",
                    color = TextColorGray,
                    fontSize = 16.sp,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }

        }
    )
}