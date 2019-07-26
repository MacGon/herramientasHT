package com.simaht.modules.dashboard_mh

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.lang.Exception
import android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
import com.example.dashboard_mh.R
import com.simaht.modules.login.view.LoginActivity

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        hideStatusBarNavigationBar()

        val background = object : Thread() {
            override fun run() {
                try {
                    Thread.sleep(1500)
                    val intent = Intent(baseContext, LoginActivity::class.java)
                    startActivity(intent)
                } catch (e: Exception){
                    e.printStackTrace()
                }
            }
        }
        background.start()
    }

    fun hideStatusBarNavigationBar(){

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

        val decorView = window.decorView
        decorView.systemUiVisibility = (SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or SYSTEM_UI_FLAG_HIDE_NAVIGATION)
    }
}
