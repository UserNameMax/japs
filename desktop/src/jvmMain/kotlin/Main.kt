import androidx.compose.ui.Alignment
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import ru.mishenko.maksim.common.App


fun main(args: Array<String>) {
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
