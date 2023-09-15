package ru.mishenko.maksim.common.ui.serverSettings

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import ru.mishenko.maksim.common.ui.root.Component

class ServerSettingsComponent(
    componentContext: ComponentContext,
    private val onBack: () -> Unit,
    private val onChat: () -> Unit
) : Component, ComponentContext by componentContext {
    @Composable
    override fun render() {
        ServerSettingsScreen(onBack = onBack, onChat = onChat)
    }
}