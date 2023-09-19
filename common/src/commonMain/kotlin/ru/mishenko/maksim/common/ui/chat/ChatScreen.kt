package ru.mishenko.maksim.common.ui.chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable

@Composable
fun ChatScreen(
    messageList: List<String>,
    inputValue: String,
    onInputValue: (String) -> Unit,
    onSend: () -> Unit,
    onBack: () -> Unit
) {
    Column {
        Column {
            messageList.forEach { Text(it) }
        }
        Row {
            TextField(value = inputValue, onValueChange = onInputValue)
            Button(onClick = onSend) { Text("send") }
        }
        Button(onClick = onBack) { Text("Back") }
    }
}