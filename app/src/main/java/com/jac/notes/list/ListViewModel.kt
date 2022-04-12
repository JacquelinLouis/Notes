package com.jac.notes.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jac.notes.data.DataNote
import com.jac.notes.data.DataRepository
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ListViewModel: ViewModel(), KoinComponent {

    private val dataRepository: DataRepository by inject()

    val notes = dataRepository.read()

    fun delete(note: DataNote) {
        viewModelScope.launch {
            dataRepository.delete(note)
        }
    }

}