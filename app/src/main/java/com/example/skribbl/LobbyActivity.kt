package com.example.skribbl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.net.ServerSocket
import java.util.*
import kotlin.concurrent.thread

class LobbyActivity : AppCompatActivity() {
    private var ss =  ServerSocket(9999)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lobby_activity)

        thread {
            var client = ss.accept()
            var reader = Scanner(client.getInputStream())
            Log.d("toto", "Client connected")
            Log.d("toto", reader.nextLine())

        }
    }


}