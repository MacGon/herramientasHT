package com.baz.simaht.login.extensions

import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun postDelayed(delayMillis: Long, task: () -> Unit) {
    Handler().postDelayed(task, delayMillis)
}

/**
 * Extension function to allows to any subclass of {@link AppCompatActivity} add a fragment in an easy way.
 *
 * @param fragment The fragment to be added
 * @param idContent Id of the container to put the fragment
 */
fun AppCompatActivity.addFragment(fragment: Fragment, idContent: Int) {
    this.supportFragmentManager.beginTransaction()
            .add(idContent, fragment)
            .commit()
}


/**
 * Extension function to allows to any subclass of {@link AppCompatActivity} replace a fragment in an easy way.
 *
 * @param fragment The fragment to be replaced
 * @param idContent Id of the container to put the fragment
 */
fun AppCompatActivity.replaceFragment(fragment: Fragment, idContent: Int) {
    this.supportFragmentManager.beginTransaction()
            .replace(idContent, fragment)
            .commit()
}

/**
 * Extension function to allows to any subclass of {@link Fragment} add a childFragment in an easy way.
 *
 * @param fragment The fragment to be added
 * @param idContent Id of the container to put the fragment
 */
fun Fragment.addChildFragment(fragment: Fragment, idContent: Int) {
    this.childFragmentManager.beginTransaction()
            .add(idContent, fragment)
            .commit()
}