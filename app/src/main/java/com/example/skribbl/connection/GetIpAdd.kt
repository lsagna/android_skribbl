package com.example.skribbl.connection

import android.content.Context
import android.net.wifi.WifiManager
import java.util.*


class GetIpAdd()  {

     private fun getIp(): String {
        val wm = getApplicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
         return Formatter.formatIpAddress(wm.connectionInfo.ipAddress)
    }

}