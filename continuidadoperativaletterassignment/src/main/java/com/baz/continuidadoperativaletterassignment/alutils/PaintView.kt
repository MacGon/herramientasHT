package com.baz.continuidadoperativaletterassignment.alutils

import android.content.Context
import android.graphics.*
import android.text.BoringLayout
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.MotionEvent
import android.view.View
import com.baz.continuidadoperativaletterassignment.almenu.view.ui.fragments.ALAsignatureAcceptedFragment
import kotlinx.android.synthetic.main.fragment_asignature_accepted.*

class PaintView (context: Context, attrs: AttributeSet): View(context, attrs) {

    var BRUSH_SIZE: Float = 10f
    var DEFAULT_COLOR: Int = Color.BLACK
    var DEFAULT_BG_COLOR: Int = Color.WHITE
    var TOUCH_TOLERANCE: Float = 4f
    var mX: Float? = 0.0f
    var mY: Float? = 0.0f
    lateinit var path: Path
    var mPaint: Paint
    var paths: ArrayList<FingerPath> = ArrayList()
    var currentColor: Int = 0
    var backgroundColors: Int = 0
    var strokeWidth: Float = 0f
    var emboss: Boolean = false
    var blur: Boolean = false
    var bolPaint: Boolean = false
    var mEmboss: MaskFilter
    var mBlur: MaskFilter
    lateinit var mBitmap: Bitmap
    lateinit var mCanvas: Canvas
    var mBitmapPaint: Paint = Paint(Paint.DITHER_FLAG)

    init {
        mPaint = Paint()
        mPaint.isAntiAlias = true
        mPaint.isDither = true
        mPaint.color = DEFAULT_COLOR
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeJoin = Paint.Join.ROUND
        mPaint.strokeCap = Paint.Cap.ROUND
        mPaint.xfermode = null
        mPaint.alpha = 0xff

        mEmboss = EmbossMaskFilter(floatArrayOf(1f, 1f, 1f), 0.4f, 6f, 3.5f)
        mBlur = BlurMaskFilter(5f, BlurMaskFilter.Blur.NORMAL)
        currentColor = DEFAULT_COLOR
        strokeWidth = BRUSH_SIZE

        //getWindowManager().getDefaultDisplay().getMetrics(metrics);
        /*val height: Int = metrics.heightPixels
        val width: Int = metrics.widthPixels
        mBitmap = Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888)
        mCanvas = Canvas(mBitmap)
        currentColor = DEFAULT_COLOR
        strokeWidth = BRUSH_SIZE*/

    }

    fun init(metrics: DisplayMetrics){
        val height: Int = metrics.heightPixels
        val width: Int = metrics.widthPixels
        /*mBitmap = Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888)
        mCanvas = Canvas(mBitmap)
        currentColor = DEFAULT_COLOR
        strokeWidth = BRUSH_SIZE*/
    }

    fun normal(){
        emboss = false
        blur = false
    }

    fun emboss(){
        emboss = true
        blur = false
    }

    fun blur(){
        emboss = false
        blur = true
    }

    fun clear(){
        bolPaint = false
        backgroundColors = DEFAULT_BG_COLOR
        paths.clear()
        normal()
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        canvas!!.save()
        mCanvas.drawColor(backgroundColors)
        for (fp in paths){
            mPaint.color = fp.color
            mPaint.strokeWidth = fp.strokeWidth
            mPaint.maskFilter = null

            if(fp.emboss) {
                mPaint.maskFilter = mEmboss
            }else if (fp.blur){
                mPaint.maskFilter = mBlur
            }

            mCanvas.drawPath(fp.path, mPaint)
        }

        canvas.drawBitmap(mBitmap, 0f, 0f, mBitmapPaint)
        canvas.restore()
    }

    fun touchStar(x: Float, y: Float){
        path = Path()
        val fp = FingerPath(currentColor, emboss, blur, strokeWidth, path)
        paths.add(fp)
        path.reset()
        path.moveTo(x, y)
        mX = x
        mY = y

    }

    fun touchMove(x: Float, y: Float){
        val dx:Float = Math.abs(x - this.mX!!)
        val dy:Float = Math.abs(y - this.mY!!)

        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE){
            path.quadTo(this.mX!!, this.mY!!, (x + this.mX!!) / 2, (y + this.mY!!) / 2)
            this.mX = x
            this.mY = y
        }


    }

    fun touchUp(){
        path.lineTo(this.mX!!, this.mY!!)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        val x: Float = event!!.x
        val y: Float = event.y
        bolPaint = true


        when(event!!.action){
            MotionEvent.ACTION_DOWN -> {touchStar(x, y); invalidate()}
            MotionEvent.ACTION_MOVE -> {touchMove(x, y); invalidate()}
            MotionEvent.ACTION_UP -> {touchUp(); invalidate()}
        }

        return true
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        mCanvas = Canvas(mBitmap)
    }



}