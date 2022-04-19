package com.jac.notes.data

import android.content.Context
import androidx.room.Room
import com.jac.notes.settings.SettingsDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf

class DataRepository(context: Context) {

    private val database = Room.databaseBuilder(context, DataNoteDatabase::class.java, "DataNoteDatabase").build()

    private val dataStore = SettingsDataStore(context)

    private val dao = database.getDataNoteDao()

    suspend fun createNote(note: DataNote) = dao.create(note)

    fun readNotes(id: Int = DataNote.ID.DEFAULT): Flow<List<DataNote>> = when(id) {
        DataNote.ID.NONE -> flowOf(listOf(DataNote()))
        DataNote.ID.DEFAULT -> dao.read()
        else -> dao.read(id)
    }

    suspend fun deleteNote(id: Int) = dao.delete(id)

    val settings = dataStore.settings

    suspend fun setSetting(setting: SettingsDataStore.Setting<*>) = dataStore.setSetting(setting)
}