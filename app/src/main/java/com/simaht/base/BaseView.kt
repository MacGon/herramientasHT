package com.baz.simaht.base

import android.content.Context

/**
* BaseView que cualquier vista debe implementar.
*/
interface BaseView {
    /**

     * Devuelve el contexto en el que se ejecuta la aplicación.
     * @return
     */
    fun getContext(): Context

}