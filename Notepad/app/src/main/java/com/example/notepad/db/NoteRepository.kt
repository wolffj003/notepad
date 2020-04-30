package com.example.notepad.db

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.notepad.model.Note

class NoteRepository(context: Context) {

    private val noteDao: NoteDao

    init {
        val database = NoteDb.getDatabase(context)
        noteDao = database!!.noteDao()
    }

    fun getNotes(): LiveData<Note?> {
        return noteDao.getNotes()
    }

    suspend fun updateNotes(note: Note) {
        noteDao.updateNotes(note)
    }
}
