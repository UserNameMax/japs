package ru.mishenko.maksim.common.ui.chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
    onSend: () -> Unit,
    onBack: () -> Unit
) {
    Column {
        TopBar(onBack = onBack)
        Chat(modifier = Modifier.fillMaxSize().weight(1f), messageList = messageList)
        MessageInput(
            modifier = Modifier.padding(10.dp),
            inputValue = inputValue,
            onInputValue = onInputValue,
            onSend = onSend
        )
    }
}