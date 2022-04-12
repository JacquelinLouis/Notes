package com.jac.notes.data

import android.content.Context
import androidx.room.Room
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class DataRepository(context: Context) {

    private val database = Room.databaseBuilder(context, DataNoteDatabase::class.java, "DataNoteDatabase").build()

    private val dao = database.getDataNoteDao()

    suspend fun create(note: DataNote) = dao.create(note)

    fun read(id: Int = DataNote.ID.DEFAULT): Flow<List<DataNote>> = when(id) {
        DataNote.ID.NONE -> flowOf(listOf(DataNote()))
        DataNote.ID.DEFAULT -> dao.read()
        else -> dao.read(id)
    }

    suspend fun delete(note: DataNote) = dao.delete(note)

}