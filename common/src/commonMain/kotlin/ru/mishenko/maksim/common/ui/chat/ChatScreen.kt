package ru.mishenko.maksim.common.ui.chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import kotlinx.coroutines.runBlocking
import ru.mishenko.maksim.common.domain.MessageController

@Composable
fun ChatScreen(onBack: () -> Unit) {
    var messageList by remember { mutableStateOf(listOf<String>()) }
    val message = remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val useCase by remember { mutableStateOf(MessageController.builder.setScope(scope).build()) }
    LaunchedEffect(Unit) {
        useCase.flow(this).collect { messageList += it }
    }
    Column {
        Column {
            messageList.forEach { Text(it) }
        }
        Row {
            TextField(value = message.value, onValueChange = { message.value = it })
            Button(onClick = { runBlocking { useCase.sendMessage(message.value) } }) { Text("send") }
        }
        Button(onClick = onBack) { Text("Back") }
    }
}