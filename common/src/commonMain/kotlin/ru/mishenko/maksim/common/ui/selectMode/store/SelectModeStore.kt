package ru.mishenko.maksim.common.ui.selectMode.store

import com.arkivanov.mvikotlin.core.store.Store

interface SelectModeStore : Store<SelectModeStore.Intent, SelectModeStore.State, SelectModeStore.Label> {
    sealed interface Intent {
        object OnSelectClientMode : Intent
        object OnSelectServerMode : Intent
    }

    sealed interface Label {
        object SelectClientMode : Label
        object SelectServerMode : Label
    }

    object State
}