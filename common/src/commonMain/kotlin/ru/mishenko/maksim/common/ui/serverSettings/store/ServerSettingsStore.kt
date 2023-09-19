package ru.mishenko.maksim.common.ui.serverSettings.store

import com.arkivanov.mvikotlin.core.store.Store

interface ServerSettingsStore : Store<ServerSettingsStore.Intent, ServerSettingsStore.State, Nothing> {
    sealed interface Intent {
        object OnSwitchToShatRequest : Intent
    }

    data class State(val id: String = "")
}