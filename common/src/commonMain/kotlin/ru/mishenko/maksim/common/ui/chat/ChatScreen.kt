package ru.mishenko.maksim.common.ui.chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.mishenko.maksim.common.domain.model.Message

@Composable
fun ChatScreen(
    messageList: List<Message>,
    inputValue: String,
    onInputValue: (String) -> Unit,
    onSend: () -> Unit,
    onBack: () -> Unit
) {
    Column {
        TopBar(onBack = onBack)
        Column(modifier = Modifier.fillMaxSize().weight(1f)) {
            messageList.forEach { Text("${it.author}: ${it.text}") }
        }
        Row {
            TextField(value = inputValue, onValueChange = onInputValue)
            Button(onClick = onSend) { Text("send") }
        }
    }
}

@Composable
fun TopBar(onBack: () -> Unit) {
    TopAppBar(
        title = { Text("Chat") },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(Icons.Filled.ArrowBack, contentDescription = null)
            }
        },
    )
}