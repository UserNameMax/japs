package ru.mishenko.maksim.common

import ru.mishenko.maksim.common.data.Client
import ru.mishenko.maksim.common.data.MyUnit
import ru.mishenko.maksim.common.domain.HistoryController

actual fun getUnit(historyController: HistoryController): MyUnit = Client(historyController, "")