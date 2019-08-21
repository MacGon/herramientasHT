package com.baz.continuidadoperativaletterassignment.alutils


import android.view.View
import android.view.Window


open class ALUtils {

    companion object{

    }

}


fun hideBottomBar(window: Window){
    window.decorView.apply {
        // Hide both the navigation bar and the status bar.
        // SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
        // a general rule, you should design your app to hide the status bar whenever you
        // hide the navigation bar.
        systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
    }
}

