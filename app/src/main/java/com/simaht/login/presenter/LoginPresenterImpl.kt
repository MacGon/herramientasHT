package com.baz.simaht.login.presenter
import com.baz.simaht.model.LogInInteractor
import com.simaht.login.presenter.LoginView

class LoginPresenterImpl(var loginView: LoginView?, val logInInteractor: LogInInteractor):
    LogInInteractor.OnLoginFinishedListener {

    fun validateCredentials(username: String, password: String) {
        loginView?.showProgress()
        logInInteractor.login(username, password, this)
    }

    fun onDestroy() {
        loginView = null
    }

    override fun onUsernameError() {
        //Llama a la función [block] especificada
        // con el valor `this` como su receptor y devuelve el valor` this`.
        loginView?.apply {
            setUsernameError()
            hideProgress()
        }
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




}