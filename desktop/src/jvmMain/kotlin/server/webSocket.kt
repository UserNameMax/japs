package server

import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import ru.mishenko.maksim.common.Chat

fun Application.module() {
    install(WebSockets)
    routing {
        webSocket("/chat") {
            Chat.emit("New connection")
            for (frame in incoming) {
                with(frame as? Frame.Text) {
                    if (this != null)
                        Chat.emit(readText())
                }
            }
        }
    }
}