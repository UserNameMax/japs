package ru.mishenko.maksim.common.ui.root

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.essenty.parcelable.Parcelable
import ru.mishenko.maksim.common.ui.chat.ChatComponent
import ru.mishenko.maksim.common.ui.clientSettings.ClientSettingsComponent
import ru.mishenko.maksim.common.ui.selectMode.SelectModeComponent
import ru.mishenko.maksim.common.ui.serverSettings.ServerSettingsComponent

class Root(componentContext: ComponentContext) : Component, ComponentContext by componentContext {
    private val navigation = StackNavigation<ScreenConfig>()
    private val stack = childStack(
        source = navigation,
        initialConfiguration = ScreenConfig.SelectMode,
        childFactory = ::createScreenComponent
    )

    private fun createScreenComponent(
        screenConfig: ScreenConfig,
        componentContext: ComponentContext
    ): Component = when (screenConfig) {
        ScreenConfig.Chat -> ChatComponent(componentContext = componentContext, onBack = ::back)
        ScreenConfig.ClientSetting -> ClientSettingsComponent(
            componentContext = componentContext,
            onBack = ::back,
            onChat = { navigate(ScreenConfig.Chat) })

        ScreenConfig.SelectMode -> SelectModeComponent(
            componentContext = componentContext,
            onSelectServer = { navigate(ScreenConfig.ServerSettings) },
            onSelectClient = { navigate(ScreenConfig.ClientSetting) })

        ScreenConfig.ServerSettings -> ServerSettingsComponent(
            componentContext = componentContext,
            onBack = ::back,
            onChat = { navigate(ScreenConfig.Chat) })
    }

    private fun navigate(destination: ScreenConfig) {
        navigation.push(destination)
    }

    private fun back() {
        navigation.pop()
    }

    sealed class ScreenConfig : Parcelable {
        object SelectMode : ScreenConfig()
        object ServerSettings : ScreenConfig()
        object ClientSetting : ScreenConfig()
        object Chat : ScreenConfig()
    }

    @Composable
    override fun render() {
        RootScreen(stack)
    }

}