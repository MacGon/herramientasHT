package com.baz.simaht.model

import android.widget.Toast
import com.baz.simaht.login.extensions.postDelayed
import com.simaht.modules.login.view.LoginActivity
import kotlinx.android.synthetic.main.fragment_login.*

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
        if (password.isEmpty()) {
            listener.onPasswordError()
        } else if (password != "Continua") {
            postDelayed(2000){
                when {
                    password != "Continua" -> listener.onPasswordError() //Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show()
                }
            }
        }

       //postDelayed(2000) {
       //    when {
       //        password.isEmpty() -> listener.onPasswordError()
       //        else -> listener.onSuccess()
       //    }
       //}
    }

    fun login2(password: String, listener: OnLoginFinishedListener) {
        if ( password.isEmpty()) {
            listener.onPasswordError()
        }
    }

}