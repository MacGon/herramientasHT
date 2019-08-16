package com.simaht.modules.login.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Editable
import android.text.Html
import android.text.InputFilter
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.Toast
import com.example.dashboard_mh.R
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.simaht.base.BaseActivity
import com.simaht.modules.dashboard_mh.*
import com.simaht.modules.login.model.LogInInteractor
import com.simaht.modules.login.presenter.Employee
import com.simaht.modules.login.presenter.LoginPresenterImpl
import com.simaht.modules.test_camera.view.TestCamera
import com.simaht.utils.Utils
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_create_pass.*
import kotlinx.android.synthetic.main.fragment_login.*
import java.lang.Exception
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

open class LoginActivity: BaseActivity(), LoginView {

    private val keyTipo: String = "KEY_TYPE"
    private val KEY_DATA: String = "DATA"
    private val codeScanner: Int = 1
    private val codeSuccess: Int = 200
    private var flagScaner: Boolean = false
    private lateinit var presentertwo: TestCamera
    private lateinit var gson: Gson

    override fun messageErrorLogin() {
        hideKeyboardEvent(etPasswordLogin)
        etPasswordLogin.text?.clear()
        Toast.makeText(this, "Completa los 8 caracteres de tu contreseña", Toast.LENGTH_SHORT).show()
    }

    override fun messageErrorSpace() {
        hideKeyboardEvent(etCreatePass)
        etCreatePass.text?.clear()
        Toast.makeText(this, "Tu contraseña no debe contener espacios", Toast.LENGTH_SHORT).show()
    }

    override fun onMessageError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun errorPass() {
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

    override fun messageErrorLetter() {
        hideKeyboardEvent(etCreatePass)
        etCreatePass.text?.clear()
        Toast.makeText(this, "Tu contraseña no debe contener la palabra Azteca", Toast.LENGTH_SHORT).show()
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
                println("HOLAAAAA $code")
                try {
                    val obj: JsonObject = gson.fromJson(code, JsonObject::class.java)
                    presenter.getUserInfo(obj.get("numSerie").asString,obj.get("numEmpleado").asString)
                    flagScaner = true
                } catch (e:Exception) {
                    flagScaner = false
                    onMessageError("Error al obtener información del servicio")
                    presenter.onBackPressed()
                }
            } else {
                presenter.onBackPressed()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun showNameFragment() {
        btnAccederLogin.text = "Siguiente"
        clearStack()
        val fragmentName = NameFragment()
        val transactionName = fm.beginTransaction()
        transactionName.replace(container.id, fragmentName)
        transactionName.commit()
    }

    override fun showCreateFragment() {
        clBackground.setOnClickListener { hideKeyboardEvent(etCreatePass) }
        clBackground.setOnClickListener { hideKeyboardEvent(etRepeatPass) }
        val fragmentCreate = CreatePassFragment()
        val transactionCreate = fm.beginTransaction()
        transactionCreate.replace(container.id, fragmentCreate)
        transactionCreate.commit()
    }

    @SuppressLint("SetTextI18n")
    override fun showSuccessFragment() {
        btnAccederLogin.text = "Aceptar"
        clearStack()
        val fragmentSuccess = SucessFragment()
        val transactionSuccess = fm.beginTransaction()
        transactionSuccess.replace(container.id, fragmentSuccess)
        transactionSuccess.commit()
    }

    @SuppressLint("SetTextI18n")
    override fun showLoginFragment() {
        btnAccederLogin.text = "Ingresar"
        clearStack()
        clBackground.setOnClickListener {
            hideKeyboardEvent(etPasswordLogin)
        }
        val fragmentLogin = LoginFragment()
        val transactionLogin = fm.beginTransaction()
        transactionLogin.replace(container.id, fragmentLogin)
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
    }

    override fun onResume() {
        super.onResume()
       //if (flagScaner) {
       //    flagScaner = false
       //    presenter.onButtonClick()
       //}
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
        gson = Gson()

        btnAccederLogin.setOnClickListener {
            presenter.onButtonClick()
        }

        //if (intent.extras != null) {
        //    val fragmentType: Int? = intent?.getIntExtra(keyTipo, -1)
        //    if (fragmentType != null) presenter.setItemFragment(fragmentType)
        //} else {
        //    presenter.setItemFragment(0)
        //}

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

    override fun encryptionPass(s: String): String {
        val MD5 = "MD5"
        try {
            // Sea crea el MD5 hash
            val digest = MessageDigest
                .getInstance(MD5)
            digest.update(s.toByteArray())
            val messageDigest = digest.digest()

            // Se crea la cadena Hexadecimal
            val hexString = StringBuilder()
            for (aMessageDigest in messageDigest) {
                var h = Integer.toHexString(0xFF and aMessageDigest.toInt())
                while (h.length < 2)
                    h = "0$h"
                hexString.append(h)
            }
            return hexString.toString()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        return ""
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun setPasswordCreatePass() {
        etCreatePass.error = getString(R.string.msg_error_create_pass)
        etRepeatPass.text?.clear()

    }

    override fun setRepeatPass() {
        etRepeatPass.error = getString(R.string.msg_error_repeat_pass)
    }

    override fun setPasswordErrorLogin() {
        //tilPasswordLogin.error = " "
        etPasswordLogin.error = getString(R.string.msg_subtitle_login)
    }

    override fun navigateToHome() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun setWelcomeMessage(message: String) {

    }

    override fun nextStepKeyboardCreate(){
        etCreatePass.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                presenter.onButtonClick()
                return@OnKeyListener true
            }
            false
        })

        etRepeatPass.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                presenter.onButtonClick()
                return@OnKeyListener true
            }
            false
        })
    }

    override fun nextStepKeyboardLogin() {
        etPasswordLogin.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                presenter.onButtonClick()
                return@OnKeyListener true
            }
            false
        })
    }

    override fun progressDialogShow() {
        Utils.progressDialogShow(this)
        //val drawable = getDrawable(resources, R.drawable.shape_progress_bar_circle_loading, null)
        //Utils.progressDialog.setIndeterminateDrawable(drawable)
    }

    override fun progressDialogHide() {
        Utils.progressDialogDismiss()
    }

    override fun enabledButtonFalse() {
        btnAccederLogin.isEnabled = false
    }

    override fun enabledButtonTrue() {
        btnAccederLogin.isEnabled = true
    }

    override fun errorTextInputLayoutLogin() {
        hideKeyboardEvent(etPasswordLogin)
        tilPasswordLogin.boxStrokeColor = resources.getColor(android.R.color.holo_red_light)
    }

    override fun errorTextInputLayoutCreatePass() {
        hideKeyboardEvent(etCreatePass)
        tilCreatePassword.boxStrokeColor = resources.getColor(android.R.color.holo_red_light)
    }

    override fun errorTextInputLayoutRepeatPass() {
        hideKeyboardEvent(etRepeatPass)
        tilRepeatPassword.boxStrokeColor = resources.getColor(android.R.color.holo_red_light)
    }

    @SuppressLint("ResourceType")
    override fun onBackPressed() {
        presenter.onBackPressed()
        super.onBackPressed()
    }
}
