package com.jac.notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import timber.log.Timber
import timber.log.Timber.Forest.plant


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (BuildConfig.DEBUG) {
            plant(Timber.DebugTree())
        }

        val notes = mutableListOf<Note>()
        for (i in 0 .. 5) { notes.add(Note(i, "Title$i", "Content$i")) }

        setContent {
            MainCompose(notes) {
                Timber.d("Clicked note $it")
            }
        }
    }
}