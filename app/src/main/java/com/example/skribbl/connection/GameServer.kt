package com.example.skribbl.connection

import java.io.InputStream
import java.io.OutputStream
import java.net.ServerSocket
import java.net.Socket
import java.util.*

class GameServer(playerConnect: Socket, target: Runnable?): Thread(target) {
    private val playerSocket: Socket = playerConnect
    private val playerInfo: InputStream = playerSocket.getInputStream()
    private val playerDrawing: OutputStream = playerSocket.getOutputStream()
    private var running: Boolean = false

    override fun run() {
        running = true
        while (running) {

        }
    }

}