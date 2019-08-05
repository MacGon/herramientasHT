package com.simaht.modules.login.view

interface LoginView {

    fun showProgress()
    fun hideProgress()

    fun setPasswordCreatePass()
    fun setRepeatPass()

    fun setPasswordErrorLogin()
    fun enabledEtRepeatTrue()
    fun enabledEtRepeatFalse()
    fun clearEditText()
    fun encryptionPass(s: String): String
    fun nextStepKeyboardCreate()
    fun nextStepKeyboardLogin()

    fun navigateToHome()
    fun setWelcomeMessage(message:String)

    //Carga de pantallas para Enrolamiento
    fun showWelcomeFragment()
    fun showQRFragment()
    fun openCamera()
    fun showNameFragment()
    fun showCreateFragment()
    fun createPassCheck()
    fun validationPassCheck()
    fun showSuccessFragment()
    fun showLoginFragment()
    fun loginCheck()
    fun messageError()
    fun messageErrorCreatePass()
    fun messageErrorLetter()
    fun messageErrorBank()
    fun messageErrorSal()
    fun messageErrorPolicy1()
    fun messageErrorPolicy2()
    fun errorPass()
}

