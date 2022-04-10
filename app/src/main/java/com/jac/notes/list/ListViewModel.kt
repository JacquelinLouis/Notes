package com.jac.notes.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jac.notes.Note

class ListViewModel: ViewModel() {

    private val _notes = MutableLiveData<List<Note>>()

    val notes: LiveData<List<Note>> = _notes

    private fun buildNote(id: Int): Note = Note(id,"Title$id", "Content$id")

    init {
        val notes = mutableListOf<Note>()
        for (i in 0 .. 5) { notes.add(buildNote(i)) }
        _notes.value = notes
    }

    fun createNote() {
        val oldNotes = _notes.value!!.toMutableList()
        oldNotes.add(buildNote(oldNotes.size))
        _notes.value = oldNotes
    }

}