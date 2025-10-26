package com.example.notescompose.presentation.notes.components

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notescompose.domain.model.Note
import com.example.notescompose.domain.use_case.NoteUseCases
import com.example.notescompose.domain.util.NoteOrder
import com.example.notescompose.domain.util.OrderType
import com.example.notescompose.presentation.notes.NotesEvent
import com.example.notescompose.presentation.notes.NotesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NotesViewModel @Inject constructor(

    private val noteUseCases: NoteUseCases
) : ViewModel(){

    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state

    private var recentlyDeletedNote: Note? = null

    private var getNotesJob: Job? = null

    /*val filteredNotes: StateFlow<List<Note>> =
        _state.map { state ->
            val searchQuery = state.searchQuery.lowercase()
            if (searchQuery.isBlank()) {
                state.notes // Если поисковый запрос пуст, показываем все заметки
            } else {
                state.notes.filter { note ->
                    // Фильтруем по заголовку ИЛИ по содержанию заметки
                    note.title.lowercase().contains(searchQuery) ||
                            note.content.lowercase().contains(searchQuery)
                }
            }
        }.stateIn( // Превращаем Flow в StateFlow, чтобы Composable мог его наблюдать
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList() // Начальное значение
        )*/





    init {
        getNotes(NoteOrder.Date(OrderType.Descending))
    }

    fun OnEvent(event: NotesEvent) {
        when(event) {
            is NotesEvent.Order -> {
                if (state.value.noteOrder::class == event.noteOrder::class &&
                    state.value.noteOrder.orderType == event.noteOrder.orderType
                ) {
                    return
                }
                getNotes(event.noteOrder)

            }
            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    noteUseCases.deleteNote(event.note)
                    recentlyDeletedNote = event.note
                }
            }
            is NotesEvent.RestoreNote -> {
                viewModelScope.launch {
                    noteUseCases.addNote(recentlyDeletedNote ?: return@launch)
                    recentlyDeletedNote = null
                }
            }
            is NotesEvent.ToggleOrderSection -> {
                _state.value = _state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }


            is NotesEvent.OnSearchTextChange -> {
                _state.value = _state.value.copy(
                    searchQuery = state.value.searchQuery
                )
            }
            // этот is тоже для поиска
        }
    }
    private fun getNotes(noteOrder: NoteOrder) {

        getNotesJob?.cancel()

        getNotesJob = noteUseCases.getNotes(noteOrder)
            .onEach { notes ->
            _state.value = state.value.copy(
                notes = notes,
                noteOrder = noteOrder
            )
        }
            .launchIn(viewModelScope)
    }
}


// остановился на реализации поиска
// все орткрытые файлы где есть коментарии относятся к изменению
// все опять пошло по пизде