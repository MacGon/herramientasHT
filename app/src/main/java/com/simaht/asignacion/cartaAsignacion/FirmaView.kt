package com.simaht.asignacion.cartaAsignacion

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class FirmaView(context: Context, attrs: AttributeSet): View(context, attrs){

    private val paintColor: Int = Color.BLACK
    private lateinit var drawPaint: Paint
    private lateinit var canvasPaint: Paint
    private var path: Path = Path()
    private lateinit var drawCanvas: Canvas
    private lateinit var canvasBitMap: Bitmap
    private lateinit var circlePoints: ArrayList<Point>

    init {
        super.setFocusable(true)
        super.setFocusableInTouchMode(true)
        setupPaint()
        circlePoints = ArrayList()
    }

    fun setupPaint(){
        drawPaint = Paint()
        canvasPaint = Paint(Paint.DITHER_FLAG)
        drawPaint.color = paintColor
        drawPaint.isAntiAlias = true
        drawPaint.strokeWidth = 10f
        drawPaint.style = Paint.Style.FILL
        drawPaint.strokeJoin = Paint.Join.ROUND
        drawPaint.strokeCap = Paint.Cap.ROUND
    }

    override fun onDraw(canvas: Canvas?) {
        /*canvas!!.drawBitmap(canvasBitMap, 0f, 0f, canvasPaint)
        canvas.drawPath(path, drawPaint)*/
        //canvas!!.drawPath(path, drawPaint)
        for (p in circlePoints){
            canvas!!.drawCircle((p.x).toFloat(), (p.y).toFloat(), 10f, drawPaint)
        }

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        val pointX = event!!.x
        val pointY = event.y

        /*when (event.action){
            MotionEvent.ACTION_DOWN -> {path.moveTo(pointX, pointY); /*return true*/ }
            MotionEvent.ACTION_MOVE -> {path.lineTo(pointX, pointY) }
            MotionEvent.ACTION_UP -> {drawCanvas.drawPath(path, drawPaint); path.reset()}
            else -> return false
        }*/

        circlePoints.add(Point(Math.round((pointX)), Math.round((pointY))));

        postInvalidate()
        //invalidate()
        return true
    }

    /*override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        canvasBitMap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        drawCanvas = Canvas(canvasBitMap)
    }*/

}