package ru.mishenko.maksim.common.ui.clientSettings

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun ClientSettingsScreen(onBack: () -> Unit, onChat: () -> Unit) {
    Column {
        Text("ClientSettingsScreen")
        Button(onClick = onBack) { Text("Back") }
        Button(onClick = onChat) { Text("Chat") }
    }
}