package ru.mishenko.maksim.common.ui.serverSettings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.mishenko.maksim.common.ui.root.Component
import ru.mishenko.maksim.common.ui.serverSettings.store.ServerSettingsStoreFactory

class ServerSettingsComponent(
    componentContext: ComponentContext,
    private val onBack: () -> Unit,
    private val onChat: () -> Unit
) : Component, ComponentContext by componentContext {

    private val store = ServerSettingsStoreFactory(DefaultStoreFactory()).create()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Composable
    override fun render() {
        val state by store.stateFlow.collectAsState()
        ServerSettingsScreen(ip = state.id, onBack = onBack, onChat = onChat)
    }
}