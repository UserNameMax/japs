package ru.mishenko.maksim.common.domain

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.launch
import ru.mishenko.maksim.common.data.Client
import ru.mishenko.maksim.common.data.MyUnit
import ru.mishenko.maksim.common.data.Server
import ru.mishenko.maksim.common.domain.model.Message
import ru.mishenko.maksim.common.domain.model.MessageType

class MessageController(
    val scope: CoroutineScope,
    private val history: HistoryController,
    private val unit: MyUnit
) {
    var unitId: Int = 0
    var connectionCounter: Int = 0
    var messageCounter: Long = 0

    init {
        scope.launch {
            unit.startWebSocket(connectMessage)
        }
    }

    suspend fun sendMessage(text: String) {
        val message = text.toMessage()
        history.emit(message)
        unit.sendMessage(message)
    }

    fun flow(scope: CoroutineScope = this.scope) = history.flow(scope).mapNotNull {
        when(it.type){
            MessageType.Connect -> {
                unit.sendMessage(approveConnectMessage(++connectionCounter))
                "New Connection".toMessage(author = "System")
            }
            MessageType.ApproveConnect -> {
                unitId = it.id.toIntOrNull() ?: 0
                "New Connection".toMessage(author = "System")
            }
            MessageType.Message -> {
                val message = it.copy(type = MessageType.ApproveMessage)
                unit.sendMessage(message)
                message
            }
            MessageType.ApproveMessage -> {
                it
            }
            MessageType.Other -> null
        }
    }

    private fun approveConnectMessage(clientId: Int) = Message(
        id = "$clientId",
        text = "",
        author = "",
        type = MessageType.ApproveConnect
    )

    private fun String.toMessage(author: String = "unit $unitId") = Message(id = "$unitId-${messageCounter++}", text = this, author = author, type = MessageType.Message)

    interface Builder {
        fun setServer(): Builder
        fun setClient(): Builder
        fun setScope(scope: CoroutineScope): Builder
        fun setHistoryController(history: HistoryController): Builder
        fun setServerIp(ip: String): Builder
        fun build(): MessageController
    }

    companion object {
        val connectMessage = Message(id = "", text = "", author = "", type = MessageType.Connect)
        val builder = object : Builder {
            private var isServer = true
            private var scope: CoroutineScope = CoroutineScope(Dispatchers.Default)
            private var history = HistoryController()
            private var serverIp = "192.168.1.161"

            override fun setServer() = this.apply { isServer = true }
            override fun setClient() = this.apply { isServer = false }
            override fun setScope(scope: CoroutineScope) = this.apply { this.scope = scope }
            override fun setHistoryController(history: HistoryController) = this.apply { this.history = history }
            override fun setServerIp(ip: String) = this.apply { serverIp = ip }
            override fun build() = MessageController(
                scope = scope,
                history = history,
                unit =
                if (isServer)
                    Server(historyController = history)
                else
                    Client(
                        historyController = history,
                        serverIp = serverIp
                    )
            )
        }
    }
}