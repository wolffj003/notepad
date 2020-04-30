package com.example.notepad.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notepad.model.Note

@Dao
interface NoteDao {

    @Insert
    suspend fun insertNotes(note: Note)

    @Query("SELECT * FROM noteTable LIMIT 1")
    fun getNotes(): LiveData<Note?>

    @Update
    suspend fun updateNotes(note: Note)
}


//@Dao
//interface NoteDao {
//
//    @Query("SELECT * FROM noteTable LIMIT 1")
//    fun getNotes(): LiveData<Note?>
//
//    @Query("DELETE FROM noteTable")
//    fun deleteNotes()
//
//    @Update
//    suspend fun updateNotes(reminder: Note)
//
//    @Insert
//    suspend fun insertNotes(reminder: Note)
//
//    @Delete
//    suspend fun deleteNotes(reminder: Note)
//}