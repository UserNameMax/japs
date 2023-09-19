package ru.mishenko.maksim.common.ui.serverSettings.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import ru.mishenko.maksim.common.utils.getIp

class ServerSettingsStoreFactory(private val storeFactory: StoreFactory) {
    fun create(): ServerSettingsStore =
        object : ServerSettingsStore,
            Store<ServerSettingsStore.Intent, ServerSettingsStore.State, Nothing> by storeFactory.create(
                name = "ServerSettingsStore",
                initialState = ServerSettingsStore.State(),
                executorFactory = ::ExecutorImpl,
                reducer = ReducerImpl,
                bootstrapper = BootstrapperImpl
            ) {}

    private object BootstrapperImpl : CoroutineBootstrapper<Action>() {
        override fun invoke() {
            val ips = getIp()
            val ip = ips.firstOrNull { it.count { it == '.' } == 3 } ?: ""
            dispatch(Action.SetIp(ip = ip))
        }
    }

    sealed interface Action {
        data class SetIp(val ip: String) : Action
    }

    sealed interface Message {
        data class SetIp(val ip: String) : Message
    }

    private class ExecutorImpl :
        CoroutineExecutor<ServerSettingsStore.Intent, Action, ServerSettingsStore.State, Message, Nothing>() {
        override fun executeAction(action: Action, getState: () -> ServerSettingsStore.State) = when (action) {
            is Action.SetIp -> dispatch(Message.SetIp(action.ip))
        }

        override fun executeIntent(intent: ServerSettingsStore.Intent, getState: () -> ServerSettingsStore.State) {
            TODO("Not yet implemented")
        }
    }

    private object ReducerImpl : Reducer<ServerSettingsStore.State, Message> {
        override fun ServerSettingsStore.State.reduce(msg: Message): ServerSettingsStore.State = when (msg) {
            is Message.SetIp -> copy(id = msg.ip)
        }
    }
}