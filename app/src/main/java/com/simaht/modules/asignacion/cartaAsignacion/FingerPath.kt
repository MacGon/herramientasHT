package com.simaht.modules.asignacion.cartaAsignacion

import android.graphics.Path

class FingerPath(color: Int, emboss: Boolean, blur: Boolean, strokeWidth: Float, path: Path) {

    var color: Int = 0
    var emboss: Boolean
    var blur: Boolean
    var strokeWidth: Float
    var path: Path

    init{
        this.color = color
        this.emboss = emboss
        this.blur = blur
        this.strokeWidth = strokeWidth
        this.path = path
    }


}