package com.simaht.modules.login.presenter

import android.provider.SyncStateContract.Helpers.update
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.simaht.utils.JsonFile

class Employee {


    companion object {
        const val FILENAME = "Employee.json"
    }

    @Transient

    private var gson: Gson
    private val TAG: String = Employee::class.java.name

    var empID: String? = null
    var empNombre: String? = null
    var registerFinished: Boolean

    init {
        empID = ""
        empNombre = ""
        registerFinished = false
        gson = Gson()
        loadInfoEmployee()

    }

    private fun loadInfoEmployee() {
        if (!JsonFile.existeArchivo(FILENAME)) {
            update()
        }
        val str: String = JsonFile.leerArchivo(FILENAME)
        val emp = gson.fromJson(str, JsonObject::class.java)
        if (emp != null) {
            val entrySetRegistro = emp.entrySet()

            for ((key, value) in entrySetRegistro) {
                Log.i("Employee", "$key valor $value")
                try {
                    var valor = value.asString
                    val campo = this.javaClass.getDeclaredField(key)

                    if (String::class.java == campo.type) {
                        campo.set(this, valor)
                    } else if (Double::class.javaPrimitiveType == campo.type) {
                        if (valor.isEmpty()) {
                            valor = "0"
                        }
                        val d = java.lang.Double.parseDouble(valor)
                        campo.setDouble(this, d)
                    } else if (Int::class.javaPrimitiveType == campo.type) {
                        val entero = Integer.parseInt(valor)
                        campo.set(this, entero)
                    } else if (Boolean::class.javaPrimitiveType == campo.type) {
                        val flag = valor.toBoolean()
                        campo.set(this, flag)
                    }
                } catch (ex: Exception) {
                    Log.e(TAG, "employeError:" + ex.localizedMessage)
                }

            }
        }

    }

    fun update() {
        JsonFile.guardarArchivo(FILENAME, gson.toJson(this))
    }

}