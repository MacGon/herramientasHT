package com.baz.simaht.login.extensions

import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

fun postDelayed(delayMillis: Long, task: () -> Unit) {
    Handler().postDelayed(task, delayMillis)
}

/**
 * Extension function that let us invoke fragmentTransaction functions without missing
 * commit call, any number of functions can be called before commit
 */
inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.func()
    fragmentTransaction.commitAllowingStateLoss()
}

/**
 * Extension function to allows to any subclass of {@link AppCompatActivity} add a fragment in an easy way.
 *
 * @param fragment The fragment to be added
 * @param idContent Id of the container to put the fragment
 */
fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int, backStackTag: String? = null) {
    supportFragmentManager.inTransaction {
        add(frameId, fragment)
        backStackTag?.let { addToBackStack(fragment.javaClass.name) }
    }
}


/**
 * Extension function to allows to any subclass of {@link AppCompatActivity} replace a fragment in an easy way.
 *
 * @param fragment The fragment to be replaced
 * @param idContent Id of the container to put the fragment
 */
fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int, backStackTag: String? = null) {
    supportFragmentManager.inTransaction {
        replace(frameId, fragment)
        backStackTag?.let {
            addToBackStack(it)
            Log.d("TAG", "Extension replace. add to backstack-*-*-*-*-*-*-Stack: ${fragmentManager.backStackEntryCount}")
        }
    }
}

/**
 * Extension function to allows to any subclass of {@link Fragment} add a childFragment in an easy way.
 *
 * @param fragment The fragment to be added
 * @param idContent Id of the container to put the fragment
 */
fun Fragment.addChildFragment(fragment: Fragment, idContent: Int, backStackTAG: String? = null) {
    childFragmentManager.inTransaction {
        add(idContent, fragment)
        backStackTAG?.let { addToBackStack(it)
            Log.d("TAG", "Extension add ChldFragment add to backstack-*-*-*-*-*-*-S tack: ${fragmentManager?.backStackEntryCount}")
        }
    }
}

/**
 * Extension function to allows to any subclass of {@link Fragment} replace a childFragment in an easy way.
 *
 * @param fragment The fragment to be added
 * @param idContent Id of the container to put the fragment
 */
fun Fragment.replaceChildFragment(fragment: Fragment, idContent: Int, backStackTAG: String? = null) {
    childFragmentManager.inTransaction {
        replace(idContent, fragment)
        backStackTAG?.let { addToBackStack(it) }
    }
}

/**
 * Extension function to allows send a Toast message in any Fragment in an easy way.
 *
 * @param msg conatains the string message to show in the context
 */
fun Fragment.toast(msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}