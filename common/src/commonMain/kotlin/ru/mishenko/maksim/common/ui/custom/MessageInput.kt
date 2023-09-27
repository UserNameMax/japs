package ru.mishenko.maksim.common.ui.custom

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MessageInput(
    modifier: Modifier = Modifier,
    inputValue: String,
    onInputValue: (String) -> Unit,
    onSend: () -> Unit
) {
    Row(modifier = modifier) {
        TextField(modifier = Modifier.fillMaxWidth().weight(1f), value = inputValue, onValueChange = onInputValue)
        Spacer(5.dp)
        Button(onClick = onSend) { Text("send") }
    }
}