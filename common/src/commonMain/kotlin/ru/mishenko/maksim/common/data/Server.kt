package ru.mishenko.maksim.common.data

import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json
import ru.mishenko.maksim.common.domain.HistoryController
import ru.mishenko.maksim.common.domain.model.Message

class Server(private val historyController: HistoryController) : MyUnit {
    private var session: WebSocketSession? = null
    private val ktorServer = embeddedServer(CIO, port = 8080) {
        install(WebSockets)
        routing {
            webSocket("/chat") {
                //historyController.emit("New connection")
                session = this
                for (frame in incoming) {
                    with(frame as? Frame.Text) {
                        if (this != null)
                            historyController.emit(Json.decodeFromString(readText()))
                    }
                }
            }
        }
    }

    override suspend fun startWebSocket(initMessage: Message) {
        ktorServer.start()
    }

    override suspend fun sendMessage(message: Message) {
        session?.send(Frame.Text(message.run {
            Json.encodeToString(Message.serializer(),this)
        }))
    }

    override fun messageFlow(scope: CoroutineScope): Flow<Message> = flow {}
}