package com.baz.continuidadoperativaletterassignment.alcommon

import androidx.core.content.res.TypedArrayUtils.getString
import com.baz.continuidadoperativaletterassignment.R

class ALConstants {

    enum class AMBIENTE {
        PRODUCCION,
        INTRANET,
        TEST,
        SIMULATE
    }

    companion object {
         val URL_SPRING_DESARROLLO = "http://10.50.109.13:8080/WSNPCobranzHDT/"
         val MSG_ERROR_SIGNATURE :String  = "La herramienta ya fue asignada, no se puede volver a ingresar."
         val MSG_ERROR_SERVICE: String = "Por el momento, el servicio no está disponible. Favor de intentarlo más tarde."
    }

}