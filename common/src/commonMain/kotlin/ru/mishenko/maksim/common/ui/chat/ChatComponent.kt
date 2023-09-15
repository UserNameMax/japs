package ru.mishenko.maksim.common.ui.chat

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import ru.mishenko.maksim.common.ui.root.Component

class ChatComponent(componentContext: ComponentContext, private val onBack: () -> Unit) : Component,
    ComponentContext by componentContext {
    @Composable
    override fun render() {
        ChatScreen(onBack = onBack)
    }
}