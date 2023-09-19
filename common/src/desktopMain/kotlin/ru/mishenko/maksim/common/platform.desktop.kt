package ru.mishenko.maksim.common

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import ru.mishenko.maksim.common.domain.MessageController

actual fun MessageController.Builder.setServerMode(): MessageController.Builder = setServer()
actual fun dispatcher(): CoroutineDispatcher = Dispatchers.Default