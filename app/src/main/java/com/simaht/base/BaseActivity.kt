package com.baz.simaht.base

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.EditText
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.example.dashboard_mh.R
import kotlinx.android.synthetic.main.activity_login.*

abstract class BaseActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navNotiltle()
        setContentView(setLayout())
        init(savedInstanceState)
        keyboardType()
        hideKeyboard(etUserLogin)
        hideKeyboard(etPasswordLogin)
    }


    @LayoutRes
    abstract fun setLayout():Int
    abstract fun init(savedInstanceState: Bundle?)
    abstract fun keyboardType()
    abstract fun hideKeyboard(editText: EditText)

    fun navNotiltle(){
        //No se mostrara la barra de notificaciones en titulo
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        //Inflar el layout
        setContentView(R.layout.activity_login)
    }




}