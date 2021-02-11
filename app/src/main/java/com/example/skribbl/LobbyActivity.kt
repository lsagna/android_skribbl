package com.example.skribbl

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toDrawable
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
        var avatarImg = findViewById<ImageView>(R.id.player2)
        var res: ByteArray
        var oui: Boolean = true
        var bmp: Bitmap
        thread {
            var client: Socket = ss.accept()
            val test = if (client == null) "fiou" else "tor"

            var reader = client.getInputStream()
            var writer = client.getOutputStream()
            Log.d("toto", test)
            res = reader.readBytes()
            writer.write("hi from SERVER".toByteArray())
            bmp = BitmapFactory.decodeByteArray(res, 0, res.size)
            Log.d("toto", "sent hi")
            Log.d("toto", "Client connected")
            Log.d("toto", "res: $res")
            avatarImg.setImageBitmap(bmp)
        }.join()

        launchButton.setOnClickListener {
            /*
                envoyer l'instruction pour changer d'activity
             */
            val intent = Intent (this, GameActivity::class.java)
            startActivity(intent)
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



