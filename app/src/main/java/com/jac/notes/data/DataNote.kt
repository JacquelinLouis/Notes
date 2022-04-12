package com.jac.notes.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jac.notes.data.DataNote.ID.DEFAULT

@Entity
class DataNote(
    @PrimaryKey(autoGenerate = true) val id: Int = DEFAULT,
    @ColumnInfo val title: String = "",
    @ColumnInfo val content: String = ""
) {
    object ID {
        const val NONE = -1
        const val DEFAULT = 0
    }
}