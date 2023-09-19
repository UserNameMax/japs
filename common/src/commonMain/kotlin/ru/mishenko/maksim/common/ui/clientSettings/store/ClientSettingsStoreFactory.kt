package ru.mishenko.maksim.common.ui.clientSettings.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import ru.mishenko.maksim.common.domain.MessageController
import ru.mishenko.maksim.common.utils.IpValidator.ipv4Validate

class ClientSettingsStoreFactory(private val storeFactory: StoreFactory) {

    fun create(): ClientSettingsStore =
        object : ClientSettingsStore,
            Store<ClientSettingsStore.Intent, ClientSettingsStore.State, Nothing> by storeFactory.create(
                name = "ClientSettingsStore",
                initialState = ClientSettingsStore.State(),
                executorFactory = ::ExecutorImpl,
                reducer = ReducerImpl
            ) {}

    sealed interface Message {
        data class InputId(val id: String) : Message
        data class UpdateIp(val id: String) : Message
    }

    private class ExecutorImpl :
        CoroutineExecutor<ClientSettingsStore.Intent, Nothing, ClientSettingsStore.State, Message, Nothing>() {
        override fun executeIntent(intent: ClientSettingsStore.Intent, getState: () -> ClientSettingsStore.State) =
            when (intent) {
                is ClientSettingsStore.Intent.OnInputIp -> dispatch(Message.InputId(intent.ip))
                ClientSettingsStore.Intent.OnChangeIp -> with(getState()) {
                    sendIp(ip = inputIp, oldIp = ip)
                }
            }

        private fun sendIp(ip: String, oldIp: String) {
            val newIp = if (ip.ipv4Validate()) ip else oldIp
            MessageController.builder.setServerIp(newIp)
            dispatch(Message.UpdateIp(newIp))
        }
    }

    private object ReducerImpl : Reducer<ClientSettingsStore.State, Message> {
        override fun ClientSettingsStore.State.reduce(msg: Message): ClientSettingsStore.State = when (msg) {
            is Message.InputId -> copy(inputIp = msg.id)
            is Message.UpdateIp -> copy(inputIp = msg.id, ip = msg.id)
        }
    }
}