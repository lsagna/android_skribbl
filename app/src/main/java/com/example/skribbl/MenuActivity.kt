package com.example.skribbl

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.drawable.toBitmap
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.net.Socket
import java.util.*

class MenuActivity : AppCompatActivity() {
    private var active: Boolean = false
    private var data: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val avatarImg = findViewById<ImageView>(R.id.avatarImage)
        val bmp: Bitmap = avatarImg.drawable.toBitmap()
        val bos: ByteArrayOutputStream = ByteArrayOutputStream()
        bmp.compress(Bitmap.CompressFormat.PNG, 0, bos)
        val arr: ByteArray = bos.toByteArray()
        //val ii = avatarImg.drawable.toString()
        //Log.d("toto", "$ii")
        val avatarImgName = avatarImg.tag.toString()
        joinLobby.setOnClickListener {

            //val oui = avatarImg.getTag().toString()
            val address = ipJoin.text.toString()
            val port = 9999
            /*
            if (connect.text == "connect") {
                active = true
                connect.text = "disconnect"
                CoroutineScope(Dispatchers.IO).launch {
                    client(address, port)
                }
            } else {
                active = false
                connect.text = "connect"
            }
            */
            active = true

            CoroutineScope(Dispatchers.IO).launch {
                client(address, port, arr)
            }
        }

        createLobby.setOnClickListener {
            val intent = Intent(this, LobbyActivity::class.java)
            startActivity(intent)
        }
    }

    private suspend fun client(address: String, port: Int, messge: ByteArray) {
        val connection = Socket(address, port)
        val writer = connection.getOutputStream()
        val reader = Scanner(connection.getInputStream())
        val msg = "hello"
        writer.write(messge)
        //active = false
        /*
        while (active) {
            var input = "BLA"
            input = reader.nextLine()
            runOnUiThread{Log.d("toto", "ici")}
            data = input
        }
        */
        reader.close()
        writer.close()
        connection.close()
    }
}