package com.example.skribbl

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val title = findViewById<TextView>(R.id.textView)
        title.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent);
        })
    }
}
