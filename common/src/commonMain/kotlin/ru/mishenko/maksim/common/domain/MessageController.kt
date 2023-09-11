package ru.mishenko.maksim.common.domain

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.mishenko.maksim.common.data.Client
import ru.mishenko.maksim.common.data.MyUnit
import ru.mishenko.maksim.common.data.Server

class MessageController(
    val scope: CoroutineScope,
    private val history: HistoryController,
    private val unit: MyUnit
) {
    init {
        scope.launch { unit.startWebSocket() }
    }

    suspend fun sendMessage(message: String) {
        history.emit(message)
        unit.sendMessage(message)
    }

    fun flow(scope: CoroutineScope = this.scope) = history.flow(scope)

    interface Builder {
        fun setServer(): Builder
        fun setClient(): Builder
        fun setScope(scope: CoroutineScope): Builder
        fun setHistoryController(history: HistoryController): Builder
        fun build(): MessageController
    }

    companion object {


        val builder = object : Builder {
            private var isServer = true
            private var scope: CoroutineScope = CoroutineScope(Dispatchers.Default)
            private var history = HistoryController()

            override fun setServer() = this.apply { isServer = true }
            override fun setClient() = this.apply { isServer = false }
            override fun setScope(scope: CoroutineScope) = this.apply { this.scope = scope }
            override fun setHistoryController(history: HistoryController) = this.apply { this.history = history }
            override fun build() = MessageController(scope, history, if (isServer) Server(history) else Client(history))
        }
    }
}