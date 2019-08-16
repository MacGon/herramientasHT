package com.simaht.modules.login.view

interface LoginView {

    fun setPasswordCreatePass()
    fun setRepeatPass()

    fun setPasswordErrorLogin()
    fun enabledEtRepeatTrue()
    fun enabledEtRepeatFalse()
    fun clearEditText()
    fun encryptionPass(s: String): String
    fun nextStepKeyboardCreate()
    fun nextStepKeyboardLogin()
    fun progressDialogShow()
    fun progressDialogHide()
    fun enabledButtonTrue()
    fun enabledButtonFalse()

    fun errorTextInputLayoutLogin()
    fun errorTextInputLayoutCreatePass()
    fun errorTextInputLayoutRepeatPass()

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
    fun messageErrorLetter()
    fun messageErrorBank()
    fun messageErrorSal()
    fun messageErrorPolicy1()
    fun messageErrorPolicy2()
    fun errorPass()
    fun onMessageError(error:String)
    fun messageErrorSpace()
    fun messageErrorLogin()
}

