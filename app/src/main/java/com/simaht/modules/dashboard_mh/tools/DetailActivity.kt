package com.simaht.modules.dashboard_mh.tools

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.baz.simaht.login.extensions.addFragment
import com.baz.simaht.login.extensions.replaceFragment
import com.example.dashboard_mh.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.simaht.modules.dashboard_mh.tools.employeefound.assignment.view.AssignToolManagerFragment2
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.app_bar_main.*



class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        nav_view.setOnNavigationItemSelectedListener(listenerMeu())


        val window = getWindow()
        window.setStatusBarColor(ContextCompat.getColor(this@DetailActivity, R.color.colorAccent))
        //nav_view.selectedItemId = R.id.navigation_home
        //val toolbar: Toolbar = findViewById(R.id.toolbar)
        //setSupportActionBar(toolbar)
        window.decorView.apply {
            systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        }


        replaceFragment(AssignToolManagerFragment2.getInstance(true), detailContainer.id, "AddTools")
    }

     private fun listenerMeu() = BottomNavigationView.OnNavigationItemSelectedListener { item ->
         return@OnNavigationItemSelectedListener when (item.itemId) {
             R.id.navigationSearchDetail -> {

                 true
             }
             R.id.navigationHomeDetail -> {
                 this@DetailActivity.finish()
                 true
             }
             R.id.navigationBackDetail -> {

                 true
             }
             else -> false
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

    fun hideKeyboardEvent(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun addTitle(titleBar: String) {
        toolbar.title = titleBar
    }

    fun addToBackStack(fragment: Fragment, backStackTAG: String? = null) {
        if (!backStackTAG.isNullOrEmpty())
            addFragment(fragment, container.id, backStackTAG)
        else
            addFragment(fragment, container.id)
    }

    fun addToBackStack(fragment: Fragment) {
        addToBackStack(fragment, null)
    }

    fun proceesDone() {
        supportFragmentManager.popBackStack()
        //TODO add contract fragment
        this@DetailActivity.finish() //FlowCompleted 100%
    }

}