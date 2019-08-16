package com.simaht.modules.login.presenter

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.simaht.modules.login.model.LogInInteractor
import com.simaht.modules.login.view.LoginView
import com.simaht.network.data.LoginRequestModel
import com.simaht.network.data.LoginResponseModel
import com.simaht.network.remote.RestAPI
import com.simaht.utils.JsonFile
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class LoginPresenterImpl(var loginView: LoginView?, val logInInteractor: LogInInteractor) :
    LogInInteractor.OnLoginFinishedListener {


    private val TAG: String = "LoginPresenterImpl"
    private lateinit var gson: Gson
    private lateinit var context: Context

    override fun setItemFragment(paso: Int) {
        when (paso) {
            0 -> loginView?.showWelcomeFragment()
            1 -> loginView?.showQRFragment()
            2 -> loginView?.openCamera()
            3 -> loginView?.showNameFragment()
            4 -> loginView?.showCreateFragment()
            5 -> loginView?.createPassCheck()
            6 -> loginView?.validationPassCheck()
            7 -> loginView?.showSuccessFragment()
            8 -> loginView?.showLoginFragment()
            9 -> loginView?.loginCheck()
            else -> {
                loginView?.loginCheck()
            }
        }
    }

    fun validateCredentialsCreatePass(password: String) {
        val esOk = true
        val passwordNumber = "^(?=.*[0-9]).{8,}$"
        val passwordLowerCase = "^(?=.*[a-z]).{8,}$"
        val passwordUpperCase = "(?=.*[A-Z]).{8,}$"
        //val passwordSpecialCharacter = "^(?=.*[@#$%^&+=]).{8,}$"
        val passwordMatcherNumber = Regex(passwordNumber)
        val passwordMatcherLowerCase = Regex(passwordLowerCase)
        val passwordMatcherUpperCase = Regex(passwordUpperCase)
        //val passwordCharacterSpecial= Regex(passwordSpecialCharacter)

        when (esOk) {

            /* 1 */ password.isEmpty() -> {
            onPasswordErrorCreatePass()
            loginView?.errorTextInputLayoutCreatePass()
            loginView?.enabledEtRepeatFalse()
            paso--
        }
        //    /* 2 */ password.length in 1..7 -> {
        //    loginView?.onMessageError("Tu contraseña debe contener 8 caracteres")
        //    loginView?.errorTextInputLayoutCreatePass()
        //    loginView?.enabledEtRepeatFalse()
        //    paso--
        //}
        //    /* 3 */ password.toUpperCase().contains("AZTECA") -> {
        //    loginView?.messageErrorLetter()
        //    loginView?.errorTextInputLayoutCreatePass()
        //    loginView?.enabledEtRepeatFalse()
        //    paso--
        //}
        //    /* 4 */ password.toUpperCase().contains("BANCO") -> {
        //    loginView?.messageErrorBank()
        //    loginView?.errorTextInputLayoutCreatePass()
        //    loginView?.enabledEtRepeatFalse()
        //    paso--
        //}
        //    /* 5 */ password.toUpperCase().contains("SALINAS") -> {
        //    loginView?.messageErrorSal()
        //    loginView?.errorTextInputLayoutCreatePass()
        //    loginView?.enabledEtRepeatFalse()
        //    paso--
        //}
        //    /* 6 */ password.contains(" ") -> {
        //    loginView?.messageErrorSpace()
        //    loginView?.errorTextInputLayoutCreatePass()
        //    loginView?.enabledEtRepeatFalse()
        //    paso--
        //}

        //    /* 7 */ !password.matches(passwordMatcherNumber) -> {
        //    loginView?.onMessageError("Tu contraseña debe contener al menos un número")
        //    loginView?.errorTextInputLayoutCreatePass()
        //    loginView?.enabledEtRepeatFalse()
        //    paso--
        //}

        //    /* 8 */ !password.matches(passwordMatcherLowerCase) -> {
        //    loginView?.onMessageError("Tu contraseña debe contener al menos una letra minúscula")
        //    loginView?.errorTextInputLayoutCreatePass()
        //    loginView?.enabledEtRepeatFalse()
        //    paso--
        //}

        //    /* 9 */ !password.matches(passwordMatcherUpperCase) -> {
        //    loginView?.onMessageError("Tu contraseña debe contener al menos una letra mayúscula")
        //    loginView?.errorTextInputLayoutCreatePass()
        //    loginView?.enabledEtRepeatFalse()
        //    paso--
        //}

            //    /* 6 */ validatePasswordPolicy1(password) -> {
            //    loginView?.messageErrorPolicy1()
            //    paso--
            //}
            //    /* 7 */ validatePasswordPolicy2(password) -> {
            //    loginView?.messageErrorPolicy2()
            //    paso--
            //}
            else -> {
                passwordCorrecto()
            }
        }
    }

    override fun passwordCorrecto() {
        loginView?.enabledEtRepeatTrue()
    }

    fun validateCredentialsConfirmPass(password: String, repeat: String) {
        when {
            repeat.isEmpty() -> {
                onPasswordErrorRepeat()
                loginView?.errorTextInputLayoutRepeatPass()
                paso--
            }
            password != repeat -> {
                loginView?.errorPass()
                loginView?.errorTextInputLayoutRepeatPass()
                paso--
            }
            password == repeat -> {
                onButtonClick()
                println("TEXTO PLANO: $repeat ")
                println("HASH: ${loginView?.encryptionPass(repeat)}")
            }
        }
        if (password.isEmpty() && repeat.isEmpty()) {
            loginView?.enabledEtRepeatFalse()
            loginView?.errorTextInputLayoutCreatePass()
            loginView?.errorTextInputLayoutRepeatPass()
            validateCredentialsCreatePass(password)
        }
    }

    fun validateCredentialsLogin(password: String) {
        when {
            /* 1 */ password.isEmpty() -> {
            onPasswordErrorLogin()
            loginView?.errorTextInputLayoutLogin()
            paso--
        }
            /* 2 */ password.length in 1..7 -> {
            loginView?.messageErrorLogin()
            loginView?.errorTextInputLayoutLogin()
            paso--
        }
            else -> {
                onSuccess()
                //logIn(password)
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

    fun onServiceError() {
        paso--
        setItemFragment(paso)
    }

    private var paso: Int = 0

    init {
        if (JsonFile.existeArchivo(Employee.FILENAME)) {
            val employee = Employee()
            if (employee.registerFinished) {
                paso = 8
            } else {
                paso = 3
            }
        } else {
            paso = 0
        }
        setItemFragment(paso)

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
        }
    }

    override fun onSuccess() {
        loginView?.navigateToHome()
        loginView?.progressDialogHide()
        loginView?.enabledButtonTrue()
    }

    override fun onBackPressed() {
        if (paso > 0) paso--
    }

    override fun getUserInfo(serialNum: String, employeeNum: String) {
        loginView?.progressDialogShow()
        val api = RestAPI()
        val disposable: Disposable = api.enrollment(serialNum, employeeNum).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                Log.e(TAG, response.message!!)
                if (response.code != 200) {
                    //loginView?.onMessageError("Error: ${response.message}")
                    //loginView?.progressDialogHide()
                    //paso--
                    val employee = Employee()
                    employee.empID = employeeNum
                    employee.empNombre = response.info?.name
                    employee.update()
                    onButtonClick()
                    loginView?.progressDialogHide()
                } else {
                    val employee = Employee()
                    employee.empID = employeeNum
                    employee.empNombre = response.info?.name
                    employee.update()
                    onButtonClick()
                    loginView?.progressDialogHide()
                }
            }, { error ->
                val employee = Employee()
                employee.empID = employeeNum
                //employee.empNombre = response.info?.name
                employee.update()
                onButtonClick()
                loginView?.progressDialogHide()
                //Log.e(TAG, "Error del servidor: " + error.message)
                //loginView?.onMessageError("Error del servidor: ${error.message}")
                //loginView?.progressDialogHide()
                //paso--
            })
    }

    override fun logIn(password: String) {
        loginView?.progressDialogShow()
        loginView?.enabledButtonFalse()
        val api = RestAPI()
        val employee = Employee()
        val request = LoginRequestModel(employee.empID, loginView?.encryptionPass(password))
        val disposable: Disposable = api.login(request).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                Log.e(TAG, response.message!!)
                if (response.code != 200) {
                    Log.e(TAG, "Error de contraseña: ${response.message}")
                    loginView?.onMessageError("Contraseña incorrecta")
                    loginView?.errorTextInputLayoutLogin()
                    loginView?.enabledButtonTrue()
                    loginView?.progressDialogHide()
                } else {
                    onSuccess()
                }
            }, { error ->
                Log.e(TAG, "Error del servidor: " + error.message)
                loginView?.onMessageError("Error del servidor: ${error.message}")
                loginView?.progressDialogHide()
                loginView?.enabledButtonTrue()
            })
    }

    override fun registerUser(password: String) {
        loginView?.progressDialogShow()
        loginView?.enabledButtonFalse()
        val api = RestAPI()
        val employee = Employee()
        val request = LoginRequestModel(employee.empID, loginView?.encryptionPass(password))
        val disposable: Disposable = api.registerUser(request).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                if (response.code != 200) {
                    loginView?.onMessageError("Error: ${response.message}")
                    loginView?.progressDialogHide()
                    loginView?.enabledButtonTrue()
                    loginView?.clearEditText()
                    paso = 4
                } else {
                    onRegisterSuccessfull()
                }
            }, { error ->
                Log.e(TAG, "Error del servidor: " + error.message)
                loginView?.onMessageError("Error del servidor: ${error.message}")
                loginView?.progressDialogHide()
                loginView?.enabledButtonTrue()
                loginView?.clearEditText()
                paso = 4
            })
    }

    fun onRegisterSuccessfull() {
        val employee = Employee()
        employee.registerFinished = true
        employee.update()
        loginView?.enabledButtonTrue()

        onButtonClick()
        loginView?.clearEditText()
        loginView?.progressDialogHide()
    }

    fun respuesta(r: LoginResponseModel) {
        Log.e(TAG, r.message)
    }

}