package ru.mishenko.maksim.common.ui.chat

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.mishenko.maksim.common.ui.chat.store.ChatStore
import ru.mishenko.maksim.common.ui.chat.store.ChatStoreFactory
import ru.mishenko.maksim.common.ui.root.Component

class ChatComponent(componentContext: ComponentContext, private val onBack: () -> Unit) : Component,
    ComponentContext by componentContext {
    private val store = ChatStoreFactory(DefaultStoreFactory()).create()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Composable
    override fun render() {
        val state by store.stateFlow.collectAsState()
        ChatScreen(
            messageList = state.messageHistory,
            inputValue = state.message,
            onInputValue = { message -> store.accept(ChatStore.Intent.OnInputMessage(message)) },
            onSend = { store.accept(ChatStore.Intent.OnSendMessage) },
            onBack = onBack
        )
    }
}