package ru.mishenko.maksim.common.domain

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import ru.mishenko.maksim.common.domain.model.Message

class HistoryController {
    private data class Element(val message: Message, val number: Int)

    private val mutableLastMessage = MutableStateFlow(Element(Message.default, 0))

    fun emit(message: Message) = mutableLastMessage.update { Element(message, it.number + 1) }
    fun flow(scope: CoroutineScope) = mutableLastMessage.shareIn(
        scope = scope,
        started = SharingStarted.Lazily,
        replay = 1
    ).mapNotNull {
        if (it.number > 0) it.message else null
    }
}