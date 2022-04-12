package com.jac.notes.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DataNote::class], version = 1, exportSchema = false)
abstract class DataNoteDatabase: RoomDatabase() {

    abstract fun getDataNoteDao(): DataNoteDao

}