package ru.mishenko.maksim.common.utils

object IpValidator {
    fun String.ipv4Validate(): Boolean {
        val sParts = split(".")
        val iParts = sParts.mapNotNull { it.toIntOrNull() }
        return when {
            iParts.size != 4 -> false
            iParts.max() > 255 -> false
            iParts.min() < 0 -> false
            else -> true
        }
    }
}