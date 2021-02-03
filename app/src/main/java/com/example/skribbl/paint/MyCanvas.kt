package com.example.skribbl.paint

import android.content.Context
import android.graphics.*
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import com.example.skribbl.R

private const val STROKE = 12f

class MyCanvas(context: Context) : View(context) {
    var currentX: Float = 0f
    var currentY: Float = 0f
    var motionX: Float = 0f
    var motionY: Float = 0f
    val backgroundColor = Color.WHITE
    val pencilColor = Color.BLACK

    private lateinit var extraCanvas: Canvas
    private lateinit var extraBitmap: Bitmap
    private val paint = Paint().apply {
        color = pencilColor
        isAntiAlias = true
        isDither = true
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
        strokeWidth = STROKE
    }
    private var path = Path()
    private val touchTolerance = ViewConfiguration.get(context).scaledTouchSlop

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (::extraBitmap.isInitialized) extraBitmap.recycle()
        extraBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        extraCanvas = Canvas(extraBitmap)
        extraCanvas.drawColor(backgroundColor)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawBitmap(extraBitmap, 0f, 0f, null)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        //motionX?.let { f -> event?.x }
        if(event?.x != null && event?.y != null) {
            motionX = event?.x
            motionY = event?.y
        }
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> touchStart()
            MotionEvent.ACTION_MOVE -> touchMove()
            MotionEvent.ACTION_UP -> touchUp()
        }
        return true
    }

    private fun touchStart() {
        path.reset()
        path.moveTo(motionX, motionY)
        currentX = motionX
        currentY = motionY
    }


    private fun touchMove() {
        val x = Math.abs(motionX - currentX)
        val y = Math.abs(motionY - currentY)
        if (x >= touchTolerance || y >= touchTolerance) {
            path.quadTo(currentX, currentY, (motionX + currentX) / 2, (motionY + currentY) / 2)
            currentX = motionX
            currentY = motionY
            extraCanvas.drawPath(path, paint)
        }
        invalidate()
    }

    private fun touchUp() {
        path.reset()
    }

}

