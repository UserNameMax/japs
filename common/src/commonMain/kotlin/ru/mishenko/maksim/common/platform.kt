package ru.mishenko.maksim.common

import kotlinx.coroutines.CoroutineDispatcher
import ru.mishenko.maksim.common.data.MyUnit
import ru.mishenko.maksim.common.domain.HistoryController
import ru.mishenko.maksim.common.domain.MessageController

expect fun getUnit(historyController: HistoryController): MyUnit

expect fun MessageController.Builder.setServerMode():MessageController.Builder

expect fun dispatcher(): CoroutineDispatcher