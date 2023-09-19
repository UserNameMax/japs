package ru.mishenko.maksim.common

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import ru.mishenko.maksim.common.domain.MessageController

actual fun MessageController.Builder.setServerMode(): MessageController.Builder = setClient()
actual fun dispatcher(): CoroutineDispatcher = Dispatchers.Main