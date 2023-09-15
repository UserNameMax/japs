package ru.mishenko.maksim.common.ui.selectMode

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun SelectModeScreen(onSelectServer: () -> Unit, onSelectClient: () -> Unit) {
    Column {
        Text("SelectModeScreen")
        Button(onClick = onSelectServer) { Text("Server") }
        Button(onClick = onSelectClient) { Text("Client") }
    }

}