import androidx.compose.ui.Alignment
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import ru.mishenko.maksim.common.App
import ru.mishenko.maksim.common.Chat


fun main(args: Array<String>) {
    embeddedServer(Netty, port = 8080) {
        install(io.ktor.server.websocket.WebSockets)
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
    }.start()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            state = rememberWindowState(
                position = WindowPosition.Aligned(Alignment.Center)
            )
        ) {
            App()
        }
    }
}
