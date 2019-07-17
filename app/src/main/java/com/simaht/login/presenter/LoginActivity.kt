package com.simaht.login.presenter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.inputmethod.InputMethodManager
import android.app.Activity
import android.text.InputType
import android.widget.EditText
import com.baz.simaht.base.BaseActivity
import com.baz.simaht.login.presenter.LoginPresenterImpl
import com.baz.simaht.model.LogInInteractor
import com.example.dashboard_mh.R
import com.simaht.dashboard_mh.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), LoginView {

    private val presenter = LoginPresenterImpl(this, LogInInteractor())

    override fun setLayout(): Int {
        return  R.layout.activity_login
    }

    //onCreate
    override fun init(savedInstanceState: Bundle?) {
        btnAccederLogin.setOnClickListener { validateCredentials() }
    }

    //Cambiar el tipo de teclado
    override fun keyboardType() {
        etPasswordLogin.inputType = InputType.TYPE_CLASS_NUMBER
    }

    private fun validateCredentials() {
        presenter.validateCredentials(etUserLogin.text.toString(), etPasswordLogin.text.toString())
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun showProgress() {
        progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress.visibility = View.GONE
    }

    override fun setUsernameError() {
        etUserLogin.error = getString(R.string.msg_subtitle_login)
    }

    override fun setPasswordError() {
        etPasswordLogin.error = getString(R.string.msg_subtitle_login)
    }

    override fun navigateToHome() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun hideKeyboard(editText: EditText) {
        editText.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) {
                hideKeyboardEvent(view)
            }
        }
    }

    //Metodo para ocultar teclado haciendo TAP en cualquier parte del activity
    fun hideKeyboardEvent(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

}
