package com.example.notepad

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.notepad.db.NoteRepository

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val noteRepository = NoteRepository(application.applicationContext)

    val note = noteRepository.getNotes()

}
