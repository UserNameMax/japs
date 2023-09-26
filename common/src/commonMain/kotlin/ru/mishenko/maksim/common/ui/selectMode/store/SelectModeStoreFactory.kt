package ru.mishenko.maksim.common.ui.selectMode.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import ru.mishenko.maksim.common.domain.MessageController

class SelectModeStoreFactory(private val storeFactory: StoreFactory) {
    fun create(): SelectModeStore =
        object : SelectModeStore,
            Store<SelectModeStore.Intent, SelectModeStore.State, SelectModeStore.Label> by storeFactory.create(
                name = "SelectModeStore",
                initialState = SelectModeStore.State(),
                executorFactory = ::ExecutorImpl,
                reducer = ReducerImpl
            ) {}

    sealed interface Message {
        data class ChangeSwitchValue(val newValue: Boolean) : Message
    }

    private class ExecutorImpl :
        CoroutineExecutor<SelectModeStore.Intent, Nothing, SelectModeStore.State, Message, SelectModeStore.Label>() {
        override fun executeIntent(intent: SelectModeStore.Intent, getState: () -> SelectModeStore.State) =
            when (intent) {

                SelectModeStore.Intent.OnTabSwitch -> {
                    val switchValue = !getState().switchValue
                    if (switchValue) {
                        MessageController.builder.setServer()
                    } else {
                        MessageController.builder.setServer()
                    }
                    dispatch(Message.ChangeSwitchValue(switchValue))
                }

                SelectModeStore.Intent.OnClickButton -> publish(
                    if (getState().switchValue) SelectModeStore.Label.SelectServerMode else SelectModeStore.Label.SelectClientMode
                )
            }
    }

    private object ReducerImpl : Reducer<SelectModeStore.State, Message> {
        override fun SelectModeStore.State.reduce(msg: Message): SelectModeStore.State =
            when (msg) {
                is Message.ChangeSwitchValue -> copy(switchValue = msg.newValue)
            }
    }
}