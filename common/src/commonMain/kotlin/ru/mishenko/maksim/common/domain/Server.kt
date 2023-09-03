package ru.mishenko.maksim.common.domain

import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.mishenko.maksim.common.CurrentChat

object Server : MyUnit {
    private var session: WebSocketSession? = null
    private val ktorServer = embeddedServer(CIO, port = 8080) {
        install(WebSockets)
        routing {
            webSocket("/chat") {
                CurrentChat.emit("New connection")
                session = this
                for (frame in incoming) {
                    with(frame as? Frame.Text) {
                        if (this != null)
                            CurrentChat.emit(readText())
                    }
                }
            }
        }
    }

    override suspend fun startWebSocket() {
        ktorServer.start()
    }

    override suspend fun sendMessage(message: String) {
        session?.send(Frame.Text(message))
    }

    override fun messageFlow(scope: CoroutineScope): Flow<String> = flow {}
}