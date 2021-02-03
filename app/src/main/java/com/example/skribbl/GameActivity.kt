package com.example.skribbl

import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.skribbl.paint.MyCanvas

class GameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val canvas = MyCanvas(this)

        super.onCreate(savedInstanceState)
        setContentView(canvas)
    }
}