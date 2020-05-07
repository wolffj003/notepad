package com.example.notepad.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "notesTable")
data class Notes(
    @ColumnInfo(name = "title")
    var notesTitle: String,
    @ColumnInfo(name = "text")
    var notesText: String,
    @ColumnInfo(name = "lastupdated")
    var notesLastUpdated: Date,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
) : Parcelable
