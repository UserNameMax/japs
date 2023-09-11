package ru.mishenko.maksim.common.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface MyUnit {
    suspend fun startWebSocket()
    suspend fun sendMessage(message: String)
    fun messageFlow(scope: CoroutineScope): Flow<String>
}