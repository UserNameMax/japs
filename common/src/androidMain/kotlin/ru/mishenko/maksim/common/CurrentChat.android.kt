package ru.mishenko.maksim.common

import android.util.Log

actual fun log(message: String) {
    Log.e("myLog",message)
}