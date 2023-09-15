package ru.mishenko.maksim.common.ui.root

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value

@Composable
fun RootScreen(stack: Value<ChildStack<Root.ScreenConfig, Component>>) {
    Children(
        stack = stack,
    ) {
        it.instance.render()
    }
}