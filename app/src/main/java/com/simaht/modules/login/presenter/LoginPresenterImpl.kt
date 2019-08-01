package com.simaht.modules.login.presenter

import com.baz.simaht.login.extensions.postDelayed
import com.simaht.modules.login.model.LogInInteractor
import com.simaht.modules.login.view.LoginView

class LoginPresenterImpl(var loginView: LoginView?, val logInInteractor: LogInInteractor) :
    LogInInteractor.OnLoginFinishedListener {

    override fun setItemFragment(paso:Int) {
        when (paso) {
            0 -> loginView?.showWelcomeFragment()
            1 -> loginView?.showQRFragment()
            2 -> loginView?.openCamera()
            3 -> loginView?.showNameFragment()
            4 -> loginView?.showCreateFragment()
            5 -> loginView?.createPassCheck()
            6 -> loginView?.validationPassCheck()
            7 -> loginView?.showSuccessFragment()
            8-> loginView?.showLoginFragment()
            9 -> loginView?.loginCheck()
            else -> {
                loginView?.loginCheck()
            }
        }
    }

    fun validateCredentialsCreatePass(password: String) {
        val esOk = true
        when(esOk){
            /* 1 */ password.isEmpty() -> {
            onPasswordErrorCreatePass()
            paso--
        }
            /* 2 */ password.length in 1..7 -> {
            loginView?.messageErrorCreatePass()
            paso--
        }
            /* 3 */ password.toUpperCase().contains("AZTECA") -> {
            loginView?.messageErrorLetter()
            paso--
        }
            /* 4 */ password.toUpperCase().contains("BANCO") -> {
            loginView?.messageErrorBank()
            paso--
        }
            /* 5 */ password.toUpperCase().contains("SALINAS") -> {
            loginView?.messageErrorSal()
            paso--
        }
        //    /* 6 */ validatePasswordPolicy1(password) -> {
        //    loginView?.messageErrorPolicy1()
        //    paso--
        //}
        //    /* 7 */ validatePasswordPolicy2(password) -> {
        //    loginView?.messageErrorPolicy2()
        //    paso--
        //}
            else ->  { passwordCorrecto() }
        }
    }

    override fun passwordCorrecto() {
        loginView?.enabledEtRepeatTrue()
    }

    fun validateCredentialsConfirmPass(password: String, repeat: String) {
        when {
            repeat.isEmpty() -> {
                onPasswordErrorRepeat()
                paso--
            }
            password != repeat -> {
                loginView?.confirmPass()
                paso --
            }
            password == repeat -> {
                println("ENTRAMOS PERRO")
                onButtonClick()
                loginView?.clearEditText()
            }
        }
        if (password.isEmpty() && repeat.isEmpty()){
            loginView?.enabledEtRepeatFalse()
            validateCredentialsCreatePass(password)
        }
    }

    fun validateCredentialsLogin(password: String ) {
        loginView?.showProgress()
        when {
            /* 1 */ password.isEmpty() -> onPasswordErrorLogin()

            /* 2 */ password != "Continua" -> postDelayed(1800) {
            loginView?.messageError()
            loginView?.hideProgress()
        }
            /* 3 */ password == "Continua" -> postDelayed(1800) {
            onSuccess()
        }
        }
    }

    /* Valida que una contraseña no puede tener más de 2 dígitos repetidos juntos, es decir: 12358963, abcholaj */
    override fun validatePasswordPolicy1(pwd: String): Boolean {
        var validate = true
        var i = 0
        while (i < pwd.length && validate) {
            if (i > 2) {
                val c0 = pwd[i - 3]
                val c1 = pwd[i - 2]
                val c2 = pwd[i - 1]
                val c3 = pwd[i]
                val fails = c0 == c1 && c1 == c2 && c2 == c3
                validate = !fails
            }
            i++
        }
        return validate && validatePasswordPolicy3(pwd)
    }

    /* Valida que una contraseña no puede tener digitos conseucutivos ascendentes o descendentes*/
    override fun validatePasswordPolicy2(pwd: String): Boolean {
        var validate = true
        var i = 0
        while (i < pwd.length && validate) {
            if (i > 2) {
                val c0 = pwd[i - 3]
                val c1 = pwd[i - 2]
                val c2 = pwd[i - 1]
                val c3 = pwd[i]
                val fails =
                    (c3.toInt() == c2.toInt() + 1 && c2.toInt() == c1.toInt() + 1 && c1.toInt() == c0.toInt() + 1 // Orden ascendente
                            || c3.toInt() == c2.toInt() - 1 && c2.toInt() == c1.toInt() - 1 && c1.toInt() == c0.toInt() - 1 // Orden descendente
                            )
                validate = !fails
            }
            i++
        }
        return validate
    }

    override fun validatePasswordPolicy3(pwd: String): Boolean {
        var validate = true
        var cont = 0
        var i = 0
        while (i < pwd.length && validate) {
            cont = 0
            for (a in 0..i) {
                if (pwd[a] == pwd[i]) {
                    cont++
                }
                if (cont > 3) {
                    validate = false
                    break
                }
            }
            i++
        }
        return validate
    }

    override fun onButtonClick() {
        paso++
        setItemFragment(paso)
    }

    private var paso: Int = 0

    init {
        paso = 0
    }

    fun onDestroy() {
        loginView = null
    }

    override fun onPasswordErrorCreatePass() {
        loginView?.apply {
            setPasswordCreatePass()
        }
    }

    override fun onPasswordErrorRepeat() {
        loginView?.apply {
            setRepeatPass()
        }
    }

    override fun onPasswordErrorLogin() {
        loginView?.apply {
            setPasswordErrorLogin()
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