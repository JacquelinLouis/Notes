package com.jac.notes

import android.content.Context
import com.jac.notes.data.DataRepository
import org.koin.core.module.Module
import org.koin.dsl.module

class MainModule {

    companion object {
        fun create(context: Context) = module {

            single { DataRepository(context) }

        }
    }

}