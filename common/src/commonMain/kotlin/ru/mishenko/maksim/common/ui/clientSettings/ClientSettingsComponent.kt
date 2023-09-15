package ru.mishenko.maksim.common.ui.clientSettings

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import ru.mishenko.maksim.common.ui.root.Component

class ClientSettingsComponent(
    componentContext: ComponentContext,
    private val onBack: () -> Unit,
    private val onChat: () -> Unit
) : Component, ComponentContext by componentContext {
    @Composable
    override fun render() {
        ClientSettingsScreen(onBack = onBack, onChat = onChat)
    }
}