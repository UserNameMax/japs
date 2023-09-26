package ru.mishenko.maksim.common.data

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.serialization.json.Json
import ru.mishenko.maksim.common.domain.HistoryController
import ru.mishenko.maksim.common.domain.model.Message

class Client(private val historyController: HistoryController, private val serverIp: String) : MyUnit {
    var session: ClientWebSocketSession? = null
    val ktorClient = HttpClient(CIO) {
        install(WebSockets)
    }

    override suspend fun startWebSocket(initMessage: Message) {
        ktorClient.webSocket(path = "/chat", host = serverIp, port = 8080) {
            session = this
            sendMessage(initMessage)
            for (frame in incoming) {
                with(frame as? Frame.Text) {
                    if (this != null)
                        historyController.emit(Json.decodeFromString(readText()))
                }
            }
        }
    }

    override suspend fun sendMessage(message: Message) {
        session?.send(Frame.Text(message.run {
            Json.encodeToString(Message.serializer(),this)
        }))
    }

    override fun messageFlow(scope: CoroutineScope): Flow<Message> =
        session!!.incoming.receiveAsFlow().mapNotNull { (it as? Frame.Text)?.readText() }.map { Json.decodeFromString(it) }
}