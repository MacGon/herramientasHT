package com.simaht.login.presenter

interface LoginView {

    fun showProgress()
    fun hideProgress()
    fun setUsernameError()
    fun setPasswordError()
    fun navigateToHome()
}

