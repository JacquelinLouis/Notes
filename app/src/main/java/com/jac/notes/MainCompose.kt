package com.jac.notes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.jac.notes.ui.theme.NotesTheme

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NotesTheme {
        Greeting("Android")
    }
}

@Composable
private fun NoteItem(note: Note, onClicked: (Int) -> Unit) {
    Column(modifier = Modifier.clickable(onClick = { onClicked(note.id) })) {
        Text(note.title)
        Text(note.content)
    }
}

private class MainComposePreviewParameterProvider: PreviewParameterProvider<List<Note>> {
    override val values = sequenceOf(mutableListOf<Note>().apply {
        for (i in 0 .. 5) { add(Note(i, "Title$i", "Content$i")) }
    })
}

@Preview(name = "MainCompose")
@Composable
fun MainCompose(@PreviewParameter(MainComposePreviewParameterProvider::class) notes: List<Note>,
                onNoteItemClicked: (Int) -> Unit = {}) {
    NotesTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
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