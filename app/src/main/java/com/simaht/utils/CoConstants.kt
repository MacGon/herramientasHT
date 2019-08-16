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
        SET_ACTION
    }
    companion object {
        val COME_FROM_CAMERA = "COMEFROMCAMERA"
        val SCANNER = "FRAGMENT_SCANNER"
        val ADDING_TOOLS = "ADDING_TOOLS"
        val SEARCHING_EMPLOYEE = "ADDING_TOOLS"
        val EMPLOYEE_FOUND = "ADDING_TOOLS"
        val ADDING_ACTIONS = "ADDING_TOOLS"
        val PROCESS_DONE = "ADDING_TOOLS"
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