package ru.mishenko.maksim.android

import android.app.Application
import ru.mishenko.maksim.common.utils.context

class App : Application() {
    companion object {
        lateinit var app: Application
    }

    init {
        context = this
        app = this
    }
}