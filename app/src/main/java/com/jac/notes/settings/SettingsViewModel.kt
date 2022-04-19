package com.jac.notes.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jac.notes.data.DataRepository
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent

class SettingsViewModel: ViewModel() {

    private val dataRepository: DataRepository by KoinJavaComponent.inject(DataRepository::class.java)

    val settings = dataRepository.settings

    fun set(setting: SettingsDataStore.Setting<*>) = viewModelScope.launch { dataRepository.setSetting(setting) }
}