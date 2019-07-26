package com.baz.simaht.model

import com.baz.simaht.login.extensions.postDelayed

class LogInInteractor {

    interface OnLoginFinishedListener {
        fun onPasswordError()
        fun onSuccess()
        fun onButtonClick()
        fun onBackPressed()
        fun setItemFragment(paso:Int)
    }

    //función simple que recibe el nombre de usuario y la contraseña, y hace alguna validación.
    fun login(password: String, listener: OnLoginFinishedListener) {
        //Inicio de sesión simulado.Creando un manejador para retrasar la respuesta un par de segundos
        postDelayed(2000) {
            when {
                password.isEmpty() -> listener.onPasswordError()
                else -> listener.onSuccess()
            }
        }
    }

}