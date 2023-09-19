package ru.mishenko.maksim.common.utils

import java.net.InetAddress
import java.net.NetworkInterface
import java.util.*


actual fun getIp(): List<String> {
    val mutableListOfIp = mutableListOf<String>()
    val e = NetworkInterface.getNetworkInterfaces()
    while (e.hasMoreElements()) {
        val n = e.nextElement() as NetworkInterface
        val ee: Enumeration<*> = n.inetAddresses
        while (ee.hasMoreElements()) {
            val i = ee.nextElement() as InetAddress
            mutableListOfIp.add(i.hostAddress)
        }
    }
    return mutableListOfIp
}