package ru.mishenko.maksim.common.data

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.receiveAsFlow
import ru.mishenko.maksim.common.domain.HistoryController

class Client(private val historyController: HistoryController) : MyUnit {
    var session: ClientWebSocketSession? = null
    val ktorClient = HttpClient(CIO) {
        install(WebSockets)
    }

    override suspend fun startWebSocket() {
        ktorClient.webSocket(path = "/chat", host = "192.168.1.161", port = 8080) {
            historyController.emit("New connection")
            session = this
            for (frame in incoming) {
                with(frame as? Frame.Text) {
                    if (this != null)
                        historyController.emit(readText())
                }
            }
        }
    }

    override suspend fun sendMessage(message: String) {
        session?.send(Frame.Text(message))
    }

    override fun messageFlow(scope: CoroutineScope): Flow<String> =
        session!!.incoming.receiveAsFlow().mapNotNull { (it as? Frame.Text)?.readText() }
}