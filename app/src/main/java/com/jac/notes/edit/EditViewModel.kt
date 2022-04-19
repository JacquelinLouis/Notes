package com.jac.notes.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jac.notes.data.DataNote
import com.jac.notes.data.DataRepository
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.java.KoinJavaComponent

class EditViewModel: ViewModel(), KoinComponent {

    private val dataRepository: DataRepository by KoinJavaComponent.inject(DataRepository::class.java)

    fun create(id: Int = DataNote.ID.DEFAULT, title: String, content: String) {
        viewModelScope.launch {
            dataRepository.createNote(DataNote(id, title, content))
        }
    }

    fun read(id: Int) = dataRepository.readNotes(id)

    fun delete(id: Int) {
        viewModelScope.launch {
            dataRepository.deleteNote(id)
        }
    }
}