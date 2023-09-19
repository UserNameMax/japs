package ru.mishenko.maksim.common.utils

import android.content.Context
import android.net.wifi.WifiManager
import android.text.format.Formatter

var context: Context? = null
actual fun getIp(): List<String> {

//    val context: Context = requireContext().applicationContext
    val wifiManager = context?.getSystemService(Context.WIFI_SERVICE) as? WifiManager
    return listOf(Formatter.formatIpAddress(wifiManager?.connectionInfo?.ipAddress ?: 0))
}