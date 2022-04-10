package com.jac.notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.jac.notes.list.ListViewModel
import timber.log.Timber
import timber.log.Timber.Forest.plant

/**
 * Follow https://developer.android.com/jetpack/compose/mental-model to implement a note application.
 */
class MainActivity : ComponentActivity() {

    private val listViewModel: ListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (BuildConfig.DEBUG) {
            plant(Timber.DebugTree())
        }

        setContent {
            MainCompose(listViewModel,
                { Timber.d("Clicked note $it") },
                { listViewModel.createNote() }
            )
        }
    }
}