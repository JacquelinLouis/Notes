package com.jac.notes.edit

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jac.notes.R
import com.jac.notes.data.DataNote

@Composable
fun EditCompose(editViewModel: EditViewModel = viewModel(),
                id: Int = DataNote.ID.NONE,
                enabled: Boolean = false, onSaveClicked: () -> Unit = {}) {

    val notes: List<DataNote> by editViewModel.read(id).collectAsState(listOf(DataNote()))
    val note = notes.first()

    var titleEnabled by remember { mutableStateOf(enabled) }
    var titleText by remember(note.title) { mutableStateOf(note.title) }

    var contentEnabled by remember { mutableStateOf(enabled) }
    var contentText by remember(note.content) { mutableStateOf(note.content) }

    ConstraintLayout(Modifier.fillMaxSize()) {

        val (titleOutlinedText, contentOutlinedText, saveButton) = createRefs()

        OutlinedTextField(
            value = titleText,
            onValueChange = { titleText = it },
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(titleOutlinedText) { top.linkTo(parent.top) }
                .clickable { if (!titleEnabled) titleEnabled = !titleEnabled },
            enabled = titleEnabled,
            label = { Text(stringResource(id = R.string.create_title)) },
            singleLine = true
        )

        OutlinedTextField(
            value = contentText,
            onValueChange = { contentText = it },
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(contentOutlinedText) {
                    top.linkTo(titleOutlinedText.bottom)
                    bottom.linkTo(saveButton.top)
                    height = Dimension.fillToConstraints
                }
                .clickable { if (!contentEnabled) contentEnabled = !contentEnabled },
            enabled = contentEnabled,
            label = { Text(stringResource(id = R.string.create_content)) }
        )

        Button(
            onClick = {
                editViewModel.create(note.id, titleText, contentText)
                onSaveClicked.invoke()
                      },
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(saveButton) { bottom.linkTo(parent.bottom) } ) {
            Text(text = stringResource(id = R.string.create_save))
        }
    }
}