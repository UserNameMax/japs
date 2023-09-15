package ru.mishenko.maksim.common.ui.serverSettings

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun ServerSettingsScreen(onBack: () -> Unit, onChat: () -> Unit) {
    Column {
        Text("ServerSettingsScreen")
        Button(onClick = onBack) { Text("Back") }
        Button(onClick = onChat) { Text("Chat") }
    }
}