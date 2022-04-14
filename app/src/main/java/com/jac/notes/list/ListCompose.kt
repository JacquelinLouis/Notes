package com.jac.notes.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jac.notes.R
import com.jac.notes.data.DataNote
import com.jac.notes.ui.theme.NotesTheme

private class MainComposePreviewParameterProvider: PreviewParameterProvider<List<DataNote>> {
    override val values = sequenceOf(mutableListOf<DataNote>().apply {
        for (i in 0 .. 5) { add(DataNote(i, "Title$i", "Content$i")) }
    })
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ListCompose(MainComposePreviewParameterProvider().values.first())
}

@Composable
private fun NoteItem(note: DataNote, onClicked: (Int) -> Unit, onDestroyClicked: (Int) -> Unit) {
    Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End) {
        Column(modifier = Modifier
            .weight(1F)
            .clickable(onClick = { onClicked(note.id) })) {
            Text(note.title)
            Text(note.content)

        }
        Image(
            painter = painterResource(id = R.drawable.ic_baseline_delete_24),
            contentDescription = "Delete the note",
            alignment = Alignment.Center,
            modifier = Modifier.clickable { onDestroyClicked(note.id) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ListCompose(@PreviewParameter(MainComposePreviewParameterProvider::class) notes: List<DataNote>,
                onNoteItemClicked: (Int) -> Unit = {},
                onCreateClicked: () -> Unit = {},
                onDestroyClicked: (Int) -> Unit = {}) {
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
                    NoteItem(note, onClicked = onNoteItemClicked, onDestroyClicked)
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
    val notes = listViewModel.notes.collectAsState(emptyList())
    ListCompose(notes.value, onNoteItemClicked, onCreateClicked,
        onDestroyClicked = { id ->
            listViewModel.delete(notes.value.findLast { it.id == id }!!)
        }
    )
}