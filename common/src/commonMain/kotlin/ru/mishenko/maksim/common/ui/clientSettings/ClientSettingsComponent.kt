package ru.mishenko.maksim.common.ui.clientSettings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.mishenko.maksim.common.ui.clientSettings.store.ClientSettingsStore
import ru.mishenko.maksim.common.ui.clientSettings.store.ClientSettingsStoreFactory
import ru.mishenko.maksim.common.ui.root.Component

class ClientSettingsComponent(
    componentContext: ComponentContext,
    private val onBack: () -> Unit,
    private val onChat: () -> Unit
) : Component, ComponentContext by componentContext {
    private val store = ClientSettingsStoreFactory(DefaultStoreFactory()).create()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Composable
    override fun render() {
        val state by store.stateFlow.collectAsState()
        ClientSettingsScreen(
            inputValue = state.inputIp,
            onInputChange = { inputValue -> store.accept(ClientSettingsStore.Intent.OnInputIp(inputValue)) },
            onUpdateIp = { store.accept(ClientSettingsStore.Intent.OnChangeIp) },
            onBack = onBack,
            onChat = onChat
        )
    }
}