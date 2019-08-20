package com.baz.continuidadoperativaletterassignment.alcommon

class ALConstants {

    enum class AMBIENTE {
        PRODUCCION,
        INTRANET,
        TEST,
        SIMULATE
    }

    companion object {
        const val URL_SPRING_DESARROLLO = "http://10.50.109.13:8080/WSNPCobranzHDT/"
        const val MSG_ERROR_ASIGNATURE :String  = "El codigo QR ya esta asignado, no se puede volver a asignar."

    }

}