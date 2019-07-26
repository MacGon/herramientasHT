package com.simaht.modules.login.presenter

import android.view.View
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



    fun validateCredentials(password: String) {
        loginView?.showProgress()
        logInInteractor.login(password, this)
    }

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