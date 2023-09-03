package ru.mishenko.maksim.common

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*

object Chat {
    private data class Element(val message: String, val number: Int)

    private val mutableLastMessage = MutableStateFlow(Element("", -1));
    fun emit(message: String) = mutableLastMessage.update { Element(message, it.number + 1) }
    fun flow(scope: CoroutineScope) = mutableLastMessage.shareIn(
        scope = scope,
        started = SharingStarted.Lazily,
        replay = 1
    ).mapNotNull { if (it.number > 0) it.message else null }

}