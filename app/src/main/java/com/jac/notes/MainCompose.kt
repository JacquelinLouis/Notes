package com.jac.notes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.jac.notes.list.ListViewModel
import com.jac.notes.list.ListCompose

@Composable
fun MainCompose(listViewModel: ListViewModel,
                onNoteItemClicked: (Int) -> Unit = {},
                onCreateClicked: () -> Unit = {}) {

    val notes by listViewModel.notes.observeAsState(emptyList())

    ListCompose(notes, onNoteItemClicked, onCreateClicked)
}