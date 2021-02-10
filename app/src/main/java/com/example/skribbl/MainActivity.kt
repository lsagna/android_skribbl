package com.example.skribbl

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.net.ServerSocket
import java.net.Socket
import java.util.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val title = findViewById<TextView>(R.id.accessPaint)
        title.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
        })

        menuButton.setOnClickListener{
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
    }


}
