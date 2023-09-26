package ru.mishenko.maksim.common.ui.clientSettings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import ru.mishenko.maksim.common.ui.custom.Spacer

@Composable
fun ClientSettingsScreen(
    inputValue: String,
    onInputChange: (String) -> Unit,
    onUpdateIp: () -> Unit,
    onBack: () -> Unit,
    onChat: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Enter server ip")
        Spacer(10.dp)
        Row {
            TextField(
                value = inputValue,
                onValueChange = onInputChange,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Spacer(10.dp)
            Button(onClick = onUpdateIp) { Text("Update ip") }
        }
        Row {
            Button(onClick = onBack) { Text("Back") }
            Spacer(10.dp)
            Button(onClick = onChat) { Text("Chat") }
        }
    }
}