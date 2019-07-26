package com.simaht.modules.login.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import com.baz.simaht.base.BaseActivity
import com.baz.simaht.model.LogInInteractor
import com.example.dashboard_mh.R
import com.simaht.modules.login.presenter.LoginPresenterImpl
import com.simaht.modules.dashboard_mh.LoginFragment
import com.simaht.modules.dashboard_mh.MainActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_login.*

class LoginActivity : BaseActivity(), LoginView {

    private val keyTipo: String = "KEY_TYPE"
    private val KEY_DATA: String = "DATA"
    private val codeScanner: Int = 1
    private var flagScaner: Boolean = false
    private var doubleBackPressed: Boolean = false

    //override fun showWelcomeFragment() {
    //    val fragmentWelcome = WelcomeFragment()
    //    val transactionWelcome = fm.beginTransaction()
    //    transactionWelcome.replace(R.id.frameContainerLogin, fragmentWelcome)
    //    transactionWelcome.commit()
    //}
//
    //@SuppressLint("SetTextI18n")
    //override fun showQRFragment() {
    //    btnAccederLogin.text = "Vamos"
    //    val fragmentQR = QRFragment()
    //    val transactionQR = fm.beginTransaction()
    //    transactionQR.replace(container.id, fragmentQR)
    //    transactionQR.addToBackStack(null)
    //    transactionQR.commit()
    //}
//
    //override fun openCamera() {
    //    val intent = Intent(this@LoginActivity, TestCamera::class.java)
    //    startActivityForResult(intent, codeScanner)
    //}
//
    //override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    //    if (requestCode == codeScanner) {
    //        if (resultCode == Activity.RESULT_OK) {
    //            val code: String = data?.extras!!.getString(KEY_DATA, "")
    //            flagScaner = true
//
    //        } else {
    //            presenter.onBackPressed()
    //        }
    //    }
    //}
//
    //@SuppressLint("SetTextI18n")
    //override fun showNameFragment() {
    //    btnAccederLogin.text = "Siguiente"
    //    val fragmentName = NameFragment()
    //    val transactionName = fm.beginTransaction()
    //    transactionName.replace(container.id, fragmentName)
    //    transactionName.addToBackStack(null)
    //    transactionName.commit()
    //}
//
    //override fun showCreateFragment() {
    //    clBackground.setOnClickListener { hideKeyboardEvent(etCreatePass) }
    //    val fragmentCreate = CreatePassFragment()
    //    val transactionCreate = fm.beginTransaction()
    //    transactionCreate.replace(container.id, fragmentCreate)
    //    transactionCreate.addToBackStack(null)
    //    transactionCreate.commit()
    //}
//
    //@SuppressLint("SetTextI18n")
    //override fun showSuccessFragment() {
    //    btnAccederLogin.text = "Aceptar"
    //    val fragmentSuccess = SucessFragment()
    //    val transactionSuccess = fm.beginTransaction()
    //    transactionSuccess.replace(container.id, fragmentSuccess)
    //    transactionSuccess.addToBackStack(null)
    //    transactionSuccess.commit()
    //}
//
    //@SuppressLint("SetTextI18n")
    //override fun showLoginFragment() {
    //    btnAccederLogin.text = "Ingresar"
    //    clBackground.setOnClickListener { hideKeyboardEvent(etPasswordLogin) }
    //    val fragmentLogin = LoginFragment()
    //    val transactionLogin = fm.beginTransaction()
    //    transactionLogin.replace(container.id, fragmentLogin)
    //    transactionLogin.addToBackStack(null)
    //    transactionLogin.commit()
    //}

    override fun loginCheck() {
        validateCredentials()
    }

    fun showLoginFragment() {
            btnAccederLogin.text = "Ingresar"
            clBackground.setOnClickListener { hideKeyboardEvent(etPasswordLogin) }
            val fragmentLogin = LoginFragment()
            val transactionLogin = fm.beginTransaction()
            transactionLogin.replace(container.id, fragmentLogin)
            //transactionLogin.addToBackStack(null)
            transactionLogin.commit()
        }

    override fun onResume() {
        super.onResume()
        if (flagScaner == true) {
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

        showLoginFragment()

        btnAccederLogin.setOnClickListener {
            //presenter.onButtonClick()
            validateCredentials()
            etPasswordLogin.text?.clear()

        }

        //if (intent.extras != null) {
        //    var fragmentType: Int? = intent?.getIntExtra(keyTipo, -1)
        //    if (fragmentType != null) presenter.setItemFragment(fragmentType)
        //} else {
        //    presenter.setItemFragment(0)
        //}
    }

    private fun validateCredentials() {
        presenter.validateCredentials(etPasswordLogin.text.toString())
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

    override fun setPasswordError() {
        etPasswordLogin.error = getString(R.string.msg_subtitle_login)
    }

    override fun navigateToHome() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun setWelcomeMessage(message: String) {

    }

    //@SuppressLint("ResourceType")
    //override fun onBackPressed() {
//
    //    presenter.onBackPressed()
    //    super.onBackPressed()
       /*if (doubleBackPressed){

           super.onBackPressed()
           return
       }
       this.doubleBackPressed = true

       val view = findViewById<ConstraintLayout>(R.id.clBackground)
       val message = getString(R.string.pressbackagain)
       val duration = Snackbar.LENGTH_SHORT
       presenter.showSnackbar(view,  message, duration)

       Handler().postDelayed({
           doubleBackPressed = false
       }, 2000)*/
    //}
}
