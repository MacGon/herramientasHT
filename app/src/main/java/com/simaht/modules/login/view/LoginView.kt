package com.simaht.modules.login.view

interface LoginView {

    fun showProgress()
    fun hideProgress()
    fun setPasswordError()
    fun navigateToHome()
    fun setWelcomeMessage(message:String)

    //Carga de pantallas para Enrolamiento
    //fun showWelcomeFragment()
    //fun showQRFragment()
    //fun openCamera()
    //fun showNameFragment()
    //fun showCreateFragment()
    //fun showSuccessFragment()
    //fun showLoginFragment()
    fun loginCheck()

}

