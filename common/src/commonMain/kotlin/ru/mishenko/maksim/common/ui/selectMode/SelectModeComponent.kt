package ru.mishenko.maksim.common.ui.selectMode

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.mishenko.maksim.common.ui.root.Component
import ru.mishenko.maksim.common.ui.selectMode.store.SelectModeStore
import ru.mishenko.maksim.common.ui.selectMode.store.SelectModeStoreFactory

class SelectModeComponent(
    componentContext: ComponentContext,
    private val onSelectServer: () -> Unit,
    private val onSelectClient: () -> Unit
) : Component, ComponentContext by componentContext {
    private val store = SelectModeStoreFactory(DefaultStoreFactory()).create()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Composable
    override fun render() {
        val state by store.stateFlow.collectAsState()
        LaunchedEffect(Unit) {
            store.labels.collect { label ->
                when (label) {
                    SelectModeStore.Label.SelectClientMode -> onSelectClient()
                    SelectModeStore.Label.SelectServerMode -> onSelectServer()
                }
            }
        }
        SelectModeScreen(
            switchValue = state.switchValue,
            onChangeValue = { store.accept(SelectModeStore.Intent.OnTabSwitch) },
            onDone = { store.accept(SelectModeStore.Intent.OnClickButton) })
    }
}