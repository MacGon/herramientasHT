package com.simaht.modules.login.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import com.example.dashboard_mh.R
import com.simaht.base.BaseActivity
import com.simaht.modules.dashboard_mh.*
import com.simaht.modules.login.model.LogInInteractor
import com.simaht.modules.login.presenter.LoginPresenterImpl
import com.simaht.modules.test_camera.view.TestCamera
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_create_pass.*
import kotlinx.android.synthetic.main.fragment_login.*

open class LoginActivity : BaseActivity(), LoginView {

    private val keyTipo: String = "KEY_TYPE"
    private val KEY_DATA: String = "DATA"
    private val codeScanner: Int = 1
    private var flagScaner: Boolean = false

    override fun confirmPass() {
        hideKeyboardEvent(etRepeatPass)
        etRepeatPass.text?.clear()
        Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
    }

    override fun messageErrorPolicy2() {
        hideKeyboardEvent(etCreatePass)
        etCreatePass.text?.clear()
        Toast.makeText(this, "Tu contraseña no puede tener más de dos dígitos repetidos juntos", Toast.LENGTH_SHORT).show()
    }

    override fun messageErrorPolicy1() {
        hideKeyboardEvent(etCreatePass)
        etCreatePass.text?.clear()
        Toast.makeText(this, "Tu contraseña no puede tener más de dos dígitos consecutivos", Toast.LENGTH_SHORT).show()
    }

    override fun messageErrorSal() {
        hideKeyboardEvent(etCreatePass)
        etCreatePass.text?.clear()
        Toast.makeText(this, "Tu contraseña no debe contener la palabra Salinas", Toast.LENGTH_SHORT).show()
    }

    override fun messageErrorBank() {
        hideKeyboardEvent(etCreatePass)
        etCreatePass.text?.clear()
        Toast.makeText(this, "Tu contraseña no debe contener la palabra Banco", Toast.LENGTH_SHORT).show()
    }

    override fun messageErrorCreatePass() {
        Toast.makeText(this, "Tu contraseña debe contener 8 caracteres", Toast.LENGTH_SHORT).show()
    }

    override fun messageErrorLetter() {
        hideKeyboardEvent(etCreatePass)
        etCreatePass.text?.clear()
        Toast.makeText(this, "Tu contraseña no debe contener la palabra Azteca", Toast.LENGTH_SHORT).show()
    }


    override fun messageError() {
        Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("SetTextI18n")
    override fun showWelcomeFragment() {
        btnAccederLogin.text = "Siguiente"
        val fragmentWelcome = WelcomeFragment()
        val transactionWelcome = fm.beginTransaction()
        transactionWelcome.replace(R.id.frameContainerLogin, fragmentWelcome)
        transactionWelcome.commit()
    }

    @SuppressLint("SetTextI18n")
    override fun showQRFragment() {
        btnAccederLogin.text = "Vamos"
        val fragmentQR = QRFragment()
        val transactionQR = fm.beginTransaction()
        transactionQR.replace(container.id, fragmentQR)
        transactionQR.addToBackStack(null)
        transactionQR.commit()
    }

    override fun openCamera() {
        val intent = Intent(this@LoginActivity, TestCamera::class.java)
        startActivityForResult(intent, codeScanner)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == codeScanner) {
            if (resultCode == Activity.RESULT_OK) {
                val code: String = data?.extras!!.getString(KEY_DATA, "")
                flagScaner = true

            } else {
                presenter.onBackPressed()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun showNameFragment() {
        btnAccederLogin.text = "Siguiente"
        val fragmentName = NameFragment()
        val transactionName = fm.beginTransaction()
        transactionName.replace(container.id, fragmentName)
        transactionName.addToBackStack(null)
        transactionName.commit()
    }

    override fun showCreateFragment() {
        clBackground.setOnClickListener { hideKeyboardEvent(etCreatePass) }
        clBackground.setOnClickListener { hideKeyboardEvent(etRepeatPass) }
        val fragmentCreate = CreatePassFragment()
        val transactionCreate = fm.beginTransaction()
        transactionCreate.replace(container.id, fragmentCreate)
        transactionCreate.addToBackStack(null)
        transactionCreate.commit()
    }

    @SuppressLint("SetTextI18n")
    override fun showSuccessFragment() {
        btnAccederLogin.text = "Aceptar"
        val fragmentSuccess = SucessFragment()
        val transactionSuccess = fm.beginTransaction()
        transactionSuccess.replace(container.id, fragmentSuccess)
        transactionSuccess.addToBackStack(null)
        transactionSuccess.commit()
    }

    @SuppressLint("SetTextI18n")
    override fun showLoginFragment() {
        btnAccederLogin.text = "Ingresar"
        clBackground.setOnClickListener { hideKeyboardEvent(etPasswordLogin) }
        val fragmentLogin = LoginFragment()
        val transactionLogin = fm.beginTransaction()
        transactionLogin.replace(container.id, fragmentLogin)
        transactionLogin.addToBackStack(null)
        transactionLogin.commit()
    }

    override fun loginCheck() {
        validateCredentialsLogin()
    }

    override fun createPassCheck() {
        validateCredentialCreatePass()
    }

    override fun validationPassCheck() {
        validateCredentialsRepeatPass()
        //setValidationPass()
    }

    override fun onResume() {
        super.onResume()
        if (flagScaner) {
            flagScaner = false
            presenter.onButtonClick()
        }
    }

    private lateinit var presenter: LoginPresenterImpl
    val fm = supportFragmentManager
    lateinit var container: FrameLayout

    override fun setLayout(): Int {
        return R.layout.activity_login
    }

    //onCreate
    @SuppressLint("SetTextI18n")
    override fun initView(savedInstanceState: Bundle?) {
        container = findViewById(R.id.frameContainerLogin)
        presenter = LoginPresenterImpl(this, LogInInteractor())

        btnAccederLogin.setOnClickListener {
            presenter.onButtonClick()
        }

        if (intent.extras != null) {
            val fragmentType: Int? = intent?.getIntExtra(keyTipo, -1)
            if (fragmentType != null) presenter.setItemFragment(fragmentType)
        } else {
            presenter.setItemFragment(0)
        }
    }

    private fun validateCredentialCreatePass() {
        presenter.validateCredentialsCreatePass(etCreatePass.text.toString())
    }

    private fun validateCredentialsRepeatPass() {
        presenter.validateCredentialsConfirmPass(etCreatePass.text.toString(), etRepeatPass.text.toString())
    }

    private fun validateCredentialsLogin() {
        presenter.validateCredentialsLogin(etPasswordLogin.text.toString())
        etPasswordLogin.text?.clear()
    }

    override fun enabledEtRepeatTrue() {
        etRepeatPass.isEnabled = true
    }

    override fun enabledEtRepeatFalse() {
        etRepeatPass.isEnabled = false
    }

    override fun clearEditText() {
        etCreatePass.text?.clear()
        etRepeatPass.text?.clear()
    }

    override fun encryptionPass() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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

    override fun setPasswordCreatePass() {
        etCreatePass.error = getString(R.string.msg_error_create_pass)
    }

    override fun setRepeatPass() {
        etRepeatPass.error = getString(R.string.msg_error_repeat_pass)
    }

    override fun setPasswordErrorLogin() {
        etPasswordLogin.error = getString(R.string.msg_subtitle_login)
    }

    override fun navigateToHome() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun setWelcomeMessage(message: String) {

    }

    @SuppressLint("ResourceType")
    override fun onBackPressed() {
        presenter.onBackPressed()
        super.onBackPressed()
    }

}
