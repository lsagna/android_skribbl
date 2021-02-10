package com.example.skribbl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.lobby_activity.*
import java.net.Inet4Address
import java.net.NetworkInterface
import java.net.ServerSocket
import java.net.Socket
import java.util.*
import kotlin.concurrent.thread

class LobbyActivity : AppCompatActivity() {
    private var ss = ServerSocket(9999)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lobby_activity)
        ipText.text = "IP address to share : " + getIpv4HostAddress()
        Log.d("toto", "ip : " + getIpv4HostAddress())
        thread {
            var client: Socket = ss.accept()
            val test = if (client == null) "fiou" else "tor"
            var reader = Scanner(client.getInputStream())
            var writer = client.getOutputStream()
            Log.d("toto", test)
            var res = reader.nextLine()
            writer.write("hi from SERVER".toByteArray())
            Log.d("toto", "sent hi")
            Log.d("toto", "Client connected")
            Log.d("toto", "res: $res")
        }
    }

    fun getIpv4HostAddress(): String {
        NetworkInterface.getNetworkInterfaces()?.toList()?.map { networkInterface ->
            networkInterface.inetAddresses?.toList()?.find {
                !it.isLoopbackAddress && it is Inet4Address
            }?.let { return it.hostAddress }
        }
        return ""
    }
}