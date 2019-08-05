package com.simaht.modules.login.model

class LogInInteractor {

    interface OnLoginFinishedListener {
        fun onPasswordErrorCreatePass()
        fun onPasswordErrorRepeat()
        fun onPasswordErrorLogin()
        fun validatePasswordPolicy1(pwd: String): Boolean
        fun validatePasswordPolicy2(pwd: String): Boolean
        fun validatePasswordPolicy3(pwd: String): Boolean
        fun passwordCorrecto()
        fun onSuccess()
        fun onButtonClick()
        fun onBackPressed()
        fun setItemFragment(paso:Int)

        fun getUserInfo(serialNum:String,enployeeNum:String)
        fun registerUser(password:String)
        fun logIn(password:String)
    }
}