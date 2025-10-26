package com.example.notescompose.dialog

import android.text.SpanWatcher
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
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.notescompose.presentation.add_edit_note.AddEditNoteViewModel
import com.example.notescompose.ui.theme.Black2
import com.example.notescompose.ui.theme.BlackBg
import com.example.notescompose.ui.theme.GreenNotes
import com.example.notescompose.ui.theme.RedNotes
import com.example.notescompose.ui.theme.TextColorGray

@Composable
fun DialogBack(
    dialogState: MutableState<Boolean>,
    onSubmit: (String) -> Unit,
    onBackAddNote:  () -> Unit,

) {
    val dialogText = remember {
        mutableStateOf("")
    }
    AlertDialog(onDismissRequest = {
        dialogState.value = false
    },
        containerColor = BlackBg,
        confirmButton = {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 5.dp, end = 5.dp),


            ) {

                Card(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp),

                ) {
                    TextButton(
                        onClick = {
                        onBackAddNote()
                        dialogState.value = false
                        },
                        modifier = Modifier.background(RedNotes).fillMaxWidth()
                    ) {
                        Text(
                            text = "Выйти",
                            color = Color.Black,
                            fontSize = 15.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.width(5.dp))

                Card(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp),

                ) {
                    TextButton(
                        onClick = {
                            onSubmit(dialogText.value)
                            dialogState.value = false
                        },
                        modifier = Modifier.background(GreenNotes).fillMaxWidth()
                    )
                    {

                        Text(
                            text = "Остаться",
                            color = Color.Black,
                            fontSize = 15.sp,
                        )
                    }
                }

            }
        },

        title = {

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    Arrangement.Center
                ) {
                    Card(
                        modifier = Modifier
                            .height(43.dp)
                            .width(43.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = BlackBg,
                            contentColor = TextColorGray
                        )
                    ) {

                    Icon(
                        modifier = Modifier.fillMaxSize(),
                        imageVector = Icons.Filled.Info,
                        contentDescription = "warning",
                    )

                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Заметка будет удалена, если выйти и не сохранить ее",
                        color = TextColorGray,
                        fontSize = 16.sp,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    )
}