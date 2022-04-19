package com.jac.notes.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.IconToggleButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jac.notes.R

private class SettingsComposePreviewParameterProvider: PreviewParameterProvider<List<SettingsDataStore.Setting<*>>> {
    override val values = sequenceOf(mutableListOf<SettingsDataStore.Setting<*>>().apply {
        SettingsDataStore.Setting(booleanPreferencesKey("LOGGING"), "Enable logging", "Enable or disable login on application's startup", false)
    })
}

@Composable
fun painterBooleanSetting(value: Boolean): Painter {
    return if (value)
        painterResource(id = R.drawable.ic_baseline_check_24)
    else
        painterResource(id = R.drawable.ic_baseline_clear_24)
}

@Composable
fun BooleanSettingItem(setting: SettingsDataStore.Setting<Boolean>, onValueChanged: (SettingsDataStore.Setting<*>) -> Unit) {
    IconToggleButton(checked = setting.value, onCheckedChange = { onValueChanged(setting.copy(value = it)) }) {
        Image(painter = painterBooleanSetting(setting.value), contentDescription = "Boolean setting check")
    }
}

@Suppress("UNCHECKED_CAST") // Setting type check is done on the setting's value, not on setting itself
@Composable
fun SettingItem(dataSetting: SettingsDataStore.Setting<*>, onValueChanged: (SettingsDataStore.Setting<*>) -> Unit = {}) {

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
        Column(modifier = Modifier.weight(1F)) {
            Text(dataSetting.title)
            Text(dataSetting.description)
        }
        when (dataSetting.value) {
            is Boolean -> BooleanSettingItem(dataSetting as SettingsDataStore.Setting<Boolean>, onValueChanged)
            else -> { /* Do nothing */ }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun SettingsCompose(@PreviewParameter(SettingsComposePreviewParameterProvider::class) dataSettings: List<SettingsDataStore.Setting<*>>,
                    onValueChanged: (SettingsDataStore.Setting<*>) -> Unit = {}) {
    LazyColumn {
        items(dataSettings) { setting ->
            SettingItem(setting, onValueChanged)
        }
    }
}

@Composable
fun SettingsCompose(settingsViewModel: SettingsViewModel = viewModel()) {
    val settings = settingsViewModel.settings.collectAsState(emptyList())
    SettingsCompose(settings.value) { settingsViewModel.set(it) }
}