package ru.mishenko.maksim.common.ui.selectMode

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import kotlinx.coroutines.launch
import ru.mishenko.maksim.common.ui.root.Component
import ru.mishenko.maksim.common.ui.selectMode.store.SelectModeStore
import ru.mishenko.maksim.common.ui.selectMode.store.SelectModeStoreFactory

class SelectModeComponent(
    componentContext: ComponentContext,
    private val onSelectServer: () -> Unit,
    private val onSelectClient: () -> Unit
) : Component, ComponentContext by componentContext {
    private val store = SelectModeStoreFactory(DefaultStoreFactory()).create()

    @Composable
    override fun render() {
        rememberCoroutineScope().launch {
            store.labels.collect { label ->
                when (label) {
                    SelectModeStore.Label.SelectClientMode -> onSelectClient()
                    SelectModeStore.Label.SelectServerMode -> onSelectServer()
                }
            }
        }
        SelectModeScreen(
            onSelectServer = { store.accept(SelectModeStore.Intent.OnSelectServerMode) },
            onSelectClient = { store.accept(SelectModeStore.Intent.OnSelectClientMode) })
    }
}