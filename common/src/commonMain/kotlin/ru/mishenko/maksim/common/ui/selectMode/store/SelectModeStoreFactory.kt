package ru.mishenko.maksim.common.ui.selectMode.store

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import ru.mishenko.maksim.common.domain.MessageController

class SelectModeStoreFactory(private val storeFactory: StoreFactory) {
    fun create(): SelectModeStore =
        object : SelectModeStore,
            Store<SelectModeStore.Intent, SelectModeStore.State, SelectModeStore.Label> by storeFactory.create(
                name = "",
                initialState = SelectModeStore.State,
                executorFactory = ::ExecutorImpl
            ) {}

    private class ExecutorImpl :
        CoroutineExecutor<SelectModeStore.Intent, Nothing, SelectModeStore.State, Nothing, SelectModeStore.Label>() {
        override fun executeIntent(intent: SelectModeStore.Intent, getState: () -> SelectModeStore.State) =
            when (intent) {
                SelectModeStore.Intent.OnSelectClientMode -> {
                    MessageController.builder.setClient()
                    publish(SelectModeStore.Label.SelectClientMode)
                }

                SelectModeStore.Intent.OnSelectServerMode -> {
                    MessageController.builder.setServer()
                    publish(SelectModeStore.Label.SelectServerMode)
                }
            }
    }
}