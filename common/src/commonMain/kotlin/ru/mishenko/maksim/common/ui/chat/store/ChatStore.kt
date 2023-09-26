package ru.mishenko.maksim.common.ui.chat.store

import com.arkivanov.mvikotlin.core.store.Store
import ru.mishenko.maksim.common.domain.model.Message

interface ChatStore : Store<ChatStore.Intent, ChatStore.State, Nothing> {
    sealed interface Intent {
        data class OnInputMessage(val message: String) : Intent
        object OnSendMessage : Intent
    }

    data class State(val message: String = "", val messageHistory: List<Message> = listOf())
}