package ru.mishenko.maksim.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Composable
fun App() {
    var messageList by remember { mutableStateOf(listOf<String>()) }
    val message = remember { mutableStateOf("") }
    val unit = getUnit()
    LaunchedEffect(Unit) {
        launch {
            unit.startWebSocket()
        }
        CurrentChat.flow(this).collect { messageList += it }
    }
    Column {
        Column {
            messageList.forEach { Text(it) }
        }
        Row {
            TextField(value = message.value, onValueChange = { message.value = it })
            Button(onClick = { runBlocking { unit.sendMessage(message.value) } }) { Text("send") }
        }
    }

}
