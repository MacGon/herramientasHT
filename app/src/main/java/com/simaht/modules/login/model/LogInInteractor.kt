package com.simaht.modules.login.model

class LogInInteractor {

    interface OnLoginFinishedListener {
        fun onPasswordError()
        fun onSuccess()
        fun onButtonClick()
        fun onBackPressed()
        fun setItemFragment(paso:Int)
    }
}