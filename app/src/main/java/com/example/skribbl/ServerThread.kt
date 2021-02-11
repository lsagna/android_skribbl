package com.example.skribbl

import android.util.Log
import java.io.IOException
import java.net.ServerSocket
import java.net.Socket

class ServerThread: Runnable {
    override fun run() {
        try {
            val ss = ServerSocket(9999);
            Log.d("toto", "Server launched")
            while (true){
                val client = ss.accept()
                val handleclient = HandleClient(s)
                handleclient.start()
            }
        } catch (e: IOException){
            e.message
        }
    }
}