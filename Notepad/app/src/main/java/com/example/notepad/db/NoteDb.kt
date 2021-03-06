package com.example.notepad.db

import android.content.Context
import android.provider.Settings.Global.getString
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.notepad.model.Notes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

@Database(entities = [Notes::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class NoteDb : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    val welcomeMsg = R.string.welcomeMsg

    companion object {
        private const val DATABASE_NAME = "NOTES_DATABASE"

        @Volatile
        private var INSTANCE: NoteDb? = null

        fun getDatabase(context: Context): NoteDb? {
            if (INSTANCE == null) {
                synchronized(NoteDb::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            NoteDb::class.java, DATABASE_NAME
                        )
                            .fallbackToDestructiveMigration()
                            .addCallback(object : RoomDatabase.Callback() {
                                override fun onCreate(db: SupportSQLiteDatabase) {
                                    super.onCreate(db)
                                    INSTANCE?.let { database ->
                                        CoroutineScope(Dispatchers.IO).launch {
                                            database.noteDao().insertNotes(Notes(
                                                notesTitle = welcomeMsg,
                                                notesLastUpdated = Date(),
                                                notesText = "")
                                            )
                                        }
                                    }
                                }
                            })
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}
