import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.singleWindowApplication
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import ru.mishenko.maksim.common.App
import ru.mishenko.maksim.common.ui.root.Root
import javax.swing.SwingUtilities

@OptIn(ExperimentalDecomposeApi::class)
fun main() {
    val windowState = WindowState()
    val lifecycle = LifecycleRegistry()
    val root = runOnMainThreadBlocking { Root(DefaultComponentContext(lifecycle)) }

    singleWindowApplication(
        state = windowState,
        title = "japs",
    ) {
        LifecycleController(lifecycle, windowState)
        App(root)
    }
}

private inline fun <T : Any> runOnMainThreadBlocking(crossinline block: () -> T): T {
    lateinit var result: T
    SwingUtilities.invokeAndWait { result = block() }
    return result
}
