package ru.mishenko.maksim.common.ui.clientSettings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun ClientSettingsScreen(
    inputValue: String,
    onInputChange: (String) -> Unit,
    onUpdateIp: () -> Unit,
    onBack: () -> Unit,
    onChat: () -> Unit
) {
    Column {
        Text("ClientSettingsScreen")
        TextField(
            value = inputValue,
            onValueChange = onInputChange,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Button(onClick = onUpdateIp) { Text("Update ip") }
        Button(onClick = onBack) { Text("Back") }
        Button(onClick = onChat) { Text("Chat") }
    }
}