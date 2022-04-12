package com.jac.notes.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DataNoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun create(vararg notes: DataNote)

    @Query("SELECT * FROM DataNote")
    fun read(): Flow<List<DataNote>>

    @Query("SELECT * FROM DataNote WHERE id = :id")
    fun read(id: Int): Flow<List<DataNote>>

    @Delete
    suspend fun delete(note: DataNote)
}