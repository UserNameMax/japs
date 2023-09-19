package ru.mishenko.maksim.common.ui.clientSettings.store

import com.arkivanov.mvikotlin.core.store.Store

interface ClientSettingsStore : Store<ClientSettingsStore.Intent, ClientSettingsStore.State, Nothing> {
    sealed interface Intent {
        data class OnInputIp(val ip: String) : Intent
        object OnChangeIp : Intent
    }

    data class State(
        val ip: String = "192.168.1.161",
        val inputIp: String = ip
    )
}