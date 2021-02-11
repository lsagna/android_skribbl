package com.example.skribbl

import java.io.IOException
import java.io.InputStream
import java.net.Socket

class HandleClient(s: Socket): Runnable {
    private val socket: Socket = s
    override fun run() {
        try {
            var reader = socket.getInputStream()
            var writer = socket.getOutputStream()
            while (true){
                try {
                    val read = reader.readBytes()

                } catch (){

                }
            }
        } catch (e: IOException){
            e.message
        }

    }
}
