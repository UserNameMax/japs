package ru.mishenko.maksim.common

import ru.mishenko.maksim.common.domain.MessageController

actual fun MessageController.Builder.setServerMode(): MessageController.Builder = setClient()