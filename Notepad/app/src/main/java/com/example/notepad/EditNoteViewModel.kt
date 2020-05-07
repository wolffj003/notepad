package com.example.notepad

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.notepad.db.NoteRepository
import com.example.notepad.model.Notes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditNoteViewModel(application: Application) : AndroidViewModel(application) {
    private val mainScope = CoroutineScope(Dispatchers.Main)

    private val noteRepository = NoteRepository(application.applicationContext)

    val notes = MutableLiveData<Notes?>()
    val error = MutableLiveData<String?>()
    val success = MutableLiveData<Boolean>()


    fun updateNotes() {
        if (notesValid()) {
            mainScope.launch { noteRepository.updateNotes(notes.value!!) }
            success.value = true
        }
    }

    private fun notesValid() : Boolean {
        return when {
            notes.value == null -> {
                error.value = "Note must not be null"
                false
            }
            notes.value!!.notesTitle.isBlank() -> {
                error.value = "Title must not be empty"
                false
            }
            else -> true
        }
    }
}
