package com.simaht.base

import android.app.Activity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideStatusBarNavigationBar()
        setContentView(setLayout())
        initView(savedInstanceState)
    }

    @LayoutRes
    abstract fun setLayout(): Int

    abstract fun initView(savedInstanceState: Bundle?)

    fun hideStatusBarNavigationBar() {

        //Toma como fondo la referencia del activity actual que en este caso es el SplashScreen STATUSBAR
        //window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        //Toma como fondo la referencia del activity actual que en este caso es el SplashScreen NAVIGATIONBAR
        //window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        //Se queda el StatusBar totalmente negro sin mostrar NADA
        //window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        /*SYSTEM_UI_FLAG_IMMERSIVE_STICKY - Cambia de color a blanco el StatusBar y se muestra el NavigationBar
        * SYSTEM_UI_FLAG_LAYOUT_STABLE - Cambia de color a blanco el StatusBar y se muestra el NavigationBa
        * SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION - El StatusBar cambia de color conforme al Splash haciendo que los iconos que contiene cambien a color blanco y se muestra el NavigationBar
        * SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN - El StatusBar cambia de color conforme al Splash haciendo que los iconos que contiene cambien a color blanco y se muestra el NavigationBar
        * SYSTEM_UI_FLAG_HIDE_NAVIGATION - El NavigationBar nativo se oculta pero el StatusBar cambia a color blanco haciendo que se vea feo
        * SYSTEM_UI_FLAG_FULLSCREEN - El StatusBar cambia a negro y no se muestra ningun componente alguno dentro de el y el NavigationBar se muestra
        * */

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        val decorView = window.decorView
        decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)
    }

    //Metodo para ocultar teclado haciendo TAP en cualquier parte del activity
    fun hideKeyboardEvent(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun keyboardType(editText: EditText) {
        editText.inputType = InputType.TYPE_CLASS_TEXT
    }

    fun clearStack(){
        val fm = this.getSupportFragmentManager()
        for (i in 0 until fm.getBackStackEntryCount()) {
            fm.popBackStack()
        }
    }

    override fun onBackPressed() {
        when {
            supportFragmentManager.backStackEntryCount > 0 -> {
                supportFragmentManager.popBackStack()
            }
            else -> {
                super.onBackPressed()
            }
        }
    }
}