package com.simaht.modules.dashboard_mh.tools

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.baz.continuidadoperativaletterassignment.almenu.view.ui.ALetterActivity
import com.baz.simaht.login.extensions.addFragment
import com.baz.simaht.login.extensions.replaceFragment
import com.example.dashboard_mh.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.simaht.modules.dashboard_mh.tools.employeefound.assignment.view.ToolTransferManagerFragment
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

        replaceFragment(ToolTransferManagerFragment.newInstance(true), detailContainer.id, "AddTools")
    }

     private fun listenerMeu() = BottomNavigationView.OnNavigationItemSelectedListener { item ->
         return@OnNavigationItemSelectedListener when (item.itemId) {
             R.id.navigationSubMenu -> {

                 true
             }
             R.id.navigationHomeDetail -> {
                 this@DetailActivity.finish()
                 true
             }
             R.id.navigationBackDetail -> {
                 goBack()
                 true
             }
             else -> false
         }
     }

    override fun onBackPressed() {
        when {
            supportFragmentManager.backStackEntryCount > 0 -> {
                goBack()
            }
            else -> {
                super.onBackPressed()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        window.decorView.apply {
            systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
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

    fun goBack(){
        if(supportFragmentManager.backStackEntryCount > 0){
            if (supportFragmentManager.fragments.contains(ToolTransferManagerFragment.getInstance())) {
                ToolTransferManagerFragment.getInstance().goBack()
            }
        } else {

        }
    }

    fun proceesDone() {
        supportFragmentManager.popBackStack()
        //TODO add contract fragment
        //this@DetailActivity.finish() //FlowCompleted 100%

        startActivity(Intent(this, ALetterActivity::class.java))
    }

}