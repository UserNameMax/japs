package ru.mishenko.maksim.common.ui.chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.mishenko.maksim.common.domain.model.Message
import ru.mishenko.maksim.common.ui.custom.Chat
import ru.mishenko.maksim.common.ui.custom.MessageInput
import ru.mishenko.maksim.common.ui.custom.TopBar

@Composable
fun ChatScreen(
    messageList: List<Message>,
    inputValue: String,
    onInputValue: (String) -> Unit,
    name: String,
    onSend: () -> Unit,
    onBack: () -> Unit,
    onChangeName: (String) -> Unit
) {
    Scaffold(
        topBar = { TopBar(name = name, onBack = onBack, onInputName = onChangeName) },
        bottomBar = {
            MessageInput(
                modifier = Modifier.padding(10.dp),
                inputValue = inputValue,
                onInputValue = onInputValue,
                onSend = onSend
            )
        }) {
        Column {
            Chat(modifier = Modifier.fillMaxSize().weight(1f), messageList = messageList)
        }
    }
}