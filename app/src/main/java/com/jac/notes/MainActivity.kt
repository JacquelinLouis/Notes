package com.jac.notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber
import timber.log.Timber.Forest.plant

/**
 * Useful tutorials used to develop this application:
 * https://developer.android.com/jetpack/compose/mental-model
 * https://developer.android.com/codelabs/jetpack-compose-navigation
 * https://developer.android.com/training/data-storage/room
 */
class MainActivity : ComponentActivity() {

    private val module by lazy { MainModule.create(applicationContext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (BuildConfig.DEBUG) {
            plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(applicationContext)
            modules(module)
        }

        setContent {
            MainCompose()
        }
    }
}