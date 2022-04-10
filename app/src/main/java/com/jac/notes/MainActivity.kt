package com.jac.notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.jac.notes.list.ListViewModel
import timber.log.Timber
import timber.log.Timber.Forest.plant

/**
 * Useful tutorials used to develop this application:
 * https://developer.android.com/jetpack/compose/mental-model
 * https://developer.android.com/codelabs/jetpack-compose-navigation
 */
class MainActivity : ComponentActivity() {

    private val listViewModel: ListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (BuildConfig.DEBUG) {
            plant(Timber.DebugTree())
        }

        setContent {
            MainCompose()
        }
    }
}