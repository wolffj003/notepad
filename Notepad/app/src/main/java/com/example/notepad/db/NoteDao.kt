package com.example.notepad.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notepad.model.Notes

@Dao
interface NoteDao {

    @Query("SELECT * FROM notesTable LIMIT 1")
    fun getNotes(): LiveData<Notes?>

    @Query("DELETE FROM notesTable")
    fun deleteNotes()

    @Update
    suspend fun updateNotes(reminder: Notes)

    @Insert
    suspend fun insertNotes(reminder: Notes)
}
