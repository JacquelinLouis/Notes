package com.jac.notes.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jac.notes.Note
import com.jac.notes.ui.theme.NotesTheme

private class MainComposePreviewParameterProvider: PreviewParameterProvider<List<Note>> {
    override val values = sequenceOf(mutableListOf<Note>().apply {
        for (i in 0 .. 5) { add(Note(i, "Title$i", "Content$i")) }
    })
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ListCompose(MainComposePreviewParameterProvider().values.first())
}

@Composable
private fun NoteItem(note: Note, onClicked: (Int) -> Unit) {
    Column(modifier = Modifier.clickable(onClick = { onClicked(note.id) })) {
        Text(note.title)
        Text(note.content)
    }
}

@Preview(showBackground = true)
@Composable
fun ListCompose(@PreviewParameter(MainComposePreviewParameterProvider::class) notes: List<Note>,
                onNoteItemClicked: (Int) -> Unit = {},
                onCreateClicked: () -> Unit = {}) {
    NotesTheme {
        // A surface container using the 'background' color from the theme
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(onClick = onCreateClicked) {
                    Icon(Icons.Filled.Add, contentDescription = "Create a new note")
                }
            },
        ) {
            LazyColumn {
                items(notes) { note ->
                    NoteItem(note, onClicked = onNoteItemClicked)
                    Divider(color = Color.Gray)
                }
            }
        }
    }
}

@Composable
fun ListCompose(listViewModel: ListViewModel = viewModel(),
                onNoteItemClicked: (Int) -> Unit = {},
                onCreateClicked: () -> Unit = {}) {
    val notes by listViewModel.notes.observeAsState(emptyList())
    ListCompose(notes, onNoteItemClicked, onCreateClicked)
}