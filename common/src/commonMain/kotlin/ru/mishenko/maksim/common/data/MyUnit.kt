package ru.mishenko.maksim.common.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import ru.mishenko.maksim.common.domain.model.Message

interface MyUnit {
    suspend fun startWebSocket()
    suspend fun sendMessage(message: Message)
    fun messageFlow(scope: CoroutineScope): Flow<Message>
}