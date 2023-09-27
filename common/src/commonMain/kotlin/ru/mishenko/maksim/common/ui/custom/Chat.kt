package ru.mishenko.maksim.common.ui.custom

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.mishenko.maksim.common.domain.model.Message

@Composable
fun Chat(
    modifier: Modifier = Modifier,
    messageList: List<Message>
) {
    LazyColumn(modifier = modifier) {
        items(messageList) {
            Text("${it.author}: ${it.text}")
        }
    }
}