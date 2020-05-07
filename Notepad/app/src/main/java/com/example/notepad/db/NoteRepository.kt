package com.example.notepad.db

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.notepad.model.Notes

class NoteRepository(context: Context) {

    private val noteDao: NoteDao

    init {
        val database = NoteDb.getDatabase(context)
        noteDao = database!!.noteDao()
    }

    fun getNotes(): LiveData<Notes?> {
        return noteDao.getNotes()
    }

    suspend fun updateNotes(notes: Notes) {
        noteDao.updateNotes(notes)
    }
}
