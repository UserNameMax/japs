package ru.mishenko.maksim.common.ui.selectMode

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import ru.mishenko.maksim.common.ui.root.Component

class SelectModeComponent(
    componentContext: ComponentContext,
    private val onSelectServer: () -> Unit,
    private val onSelectClient: () -> Unit
) :
    Component,
    ComponentContext by componentContext {
    @Composable
    override fun render() {
        SelectModeScreen(onSelectServer = onSelectServer, onSelectClient = onSelectClient)
    }
}