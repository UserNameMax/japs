package ru.mishenko.maksim.common.ui.serverSettings

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun ServerSettingsScreen(ip: String, onBack: () -> Unit, onChat: () -> Unit) {
    Column {
        Text("ServerSettingsScreen")
        Text(text = ip)
        Button(onClick = onBack) { Text("Back") }
        Button(onClick = onChat) { Text("Chat") }
    }
}