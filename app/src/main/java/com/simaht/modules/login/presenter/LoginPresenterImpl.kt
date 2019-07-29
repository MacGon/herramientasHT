package com.simaht.modules.login.presenter

import android.view.View
import android.widget.Toast
import com.baz.simaht.login.extensions.postDelayed
import com.baz.simaht.model.LogInInteractor
import com.google.android.material.snackbar.Snackbar
import com.simaht.modules.login.view.LoginView


class LoginPresenterImpl(var loginView: LoginView?, val logInInteractor: LogInInteractor) :
    LogInInteractor.OnLoginFinishedListener {

    override fun setItemFragment(paso:Int) {
        when (paso) {
           //0 -> loginView?.showWelcomeFragment()
           //1 -> loginView?.showQRFragment()
           //2 -> loginView?.openCamera()
           //3 -> loginView?.showNameFragment()
           //4 -> loginView?.showCreateFragment()
           //5 -> loginView?.showSuccessFragment()
           //6-> loginView?.showLoginFragment()
           7 -> loginView?.loginCheck()
            else -> {
                loginView?.loginCheck()
            }
        }
    }

    fun validateCredentials(password: String ) {
        loginView?.showProgress()
        //Inicio de sesión simulado.Creando un manejador para retrasar la respuesta un par de segundos
        when {
            /* 1 */ password.isEmpty() -> onPasswordError()

            /* 2 */ password != "Continua" -> postDelayed(1800) {
            loginView?.messageError()
            loginView?.hideProgress()
        }
            /* 3 */ password == "Continua" -> postDelayed(1800) {
            onSuccess()
        }
        }
    }


    //fun validateCredentials(password: String){
    //    loginView?.showProgress()
    //    loginView?.validateLogin(password, this)
    //}

    override fun onButtonClick() {
        paso++
        setItemFragment(paso)
    }

    private var paso: Int = 0

    init {
        paso = 0
    }

    fun showSnackbar(view: View, message: String, duration: Int) {
        Snackbar.make(view, message, duration).show()
        //snackbar.setActionTextColor(Color.BLUE)
        //val snackbarView = snackbar.view
        //snackbarView.setBackgroundColor(Color.LTGRAY)
        //val textView = snackbarView.findViewById<TextView>(R.string.pressbackagain)
        //val textView = snackbarView.findViewById<TextView>(R.string.pressbackagain)
        //textView.setTextColor(Color.BLUE)
        //textView.textSize = 28f
        //snackbar.show()
    }



    //fun validateCredentials(password: String) {
    //    loginView?.showProgress()
    //    logInInteractor.login(password, this)
    //}

    fun onDestroy() {
        loginView = null
    }

    override fun onPasswordError() {
        loginView?.apply {
            setPasswordError()
            hideProgress()
        }
    }

    override fun onSuccess() {
        loginView?.hideProgress()
        loginView?.navigateToHome()
    }

    override fun onBackPressed() {
        if (paso > 0) paso--
    }
}