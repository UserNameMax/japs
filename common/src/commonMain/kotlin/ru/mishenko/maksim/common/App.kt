package ru.mishenko.maksim.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import io.ktor.client.*
import io.ktor.client.plugins.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

@Composable
fun App() {
    var messageList by remember { mutableStateOf(listOf<String>()) }
    val message = remember { mutableStateOf("") }
    val sendFlag = remember { MutableStateFlow(false) }
    LaunchedEffect(Unit) {
        Chat.flow(this).collect { messageList += it }
    }
    LaunchedEffect(Unit) {
        delay(10000L)
        HttpClient {
            install(WebSockets)
        }.webSocket(path = "/chat", host = "127.0.0.1", port = 8080) {
            coroutineScope { sendFlag.collect { send(Frame.Text(message.value)) } }
        }
    }
    Column {
        Column {
            messageList.forEach { Text(it) }
        }
        Row {
            TextField(value = message.value, onValueChange = { message.value = it })
            Button(onClick = { sendFlag.update { !it } }) { Text("send") }
        }
    }

}
