package com.baz.simaht.utils

class CoConstants {

    //ambientes
    enum class AMBIENTE {
        PRODUCCION,
        TEST,
        SIMULATE
    }

    //-------------Tools Assignation -------------
    enum class ASSIGN {
        ASSIGMENT,
        COMFIRMATION
    }
    companion object {
        val COME_FROM_CAMERA = "COMEFROMCAMERA"
    }

    enum class STEP {
        ADDING_TOOL,
        SEARCHING_EMPLOYEE,
        EMPLOYEE_FOUND,
        CUSTODY,
        NEW_EMPLOYEE,
        DONE,
        FINISH
    }

    enum class ACTIONS {
        CUSTODY,
        DAMAGE_CHARGE,
        INCIDENT,
        FACTORY_DEFECT,
        END_OF_USEFUL_LIFE
    }
}