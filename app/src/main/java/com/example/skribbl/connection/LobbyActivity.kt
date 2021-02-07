package com.example.skribbl.connection

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.example.skribbl.R
import java.net.ServerSocket
import com.example.skribbl.connection.GetIpAdd
import kotlin.concurrent.thread

class LobbyActivity : AppCompatActivity() {
    private val lobbyIsUp: Boolean = true
    //private val otherPlayers: List<Thread> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lobby_activity)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val gameServer = ServerSocket(9999)
        val otherPlayers: ArrayList<Thread> = arrayListOf()
        while(lobbyIsUp) {
            val player = gameServer.accept()
            val playerThread = GameServer(player)

            otherPlayers.add(playerThread)


        }
    }
}


