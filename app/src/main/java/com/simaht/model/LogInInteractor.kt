package com.baz.simaht.model

import com.baz.simaht.login.extensions.postDelayed

class LogInInteractor {

    interface OnLoginFinishedListener {
        fun onUsernameError()
        fun onPasswordError()
        fun onSuccess()
    }

    //función simple que recibe el nombre de usuario y la contraseña, y hace alguna validación.
    fun login(username: String, password: String, listener: OnLoginFinishedListener) {
        //Inicio de sesión simulado.Creando un manejador para retrasar la respuesta un par de segundos
        postDelayed(2000) {
            when {
                username.isEmpty() -> listener.onUsernameError()
                password.isEmpty() -> listener.onPasswordError()
                else -> listener.onSuccess()
            }
        }
    }

}