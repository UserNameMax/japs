package ru.mishenko.maksim.common.ui.serverSettings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.mishenko.maksim.common.ui.custom.Spacer

@Composable
fun ServerSettingsScreen(ip: String, onBack: () -> Unit, onChat: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Your ip: $ip")
        Spacer(height = 20.dp)
        Row {
            Button(onClick = onBack) { Text("Back") }
            Spacer(width = 10.dp)
            Button(onClick = onChat) { Text("Chat") }
        }
    }
}