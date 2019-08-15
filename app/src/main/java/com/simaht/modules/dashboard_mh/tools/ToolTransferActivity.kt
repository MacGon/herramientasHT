package com.simaht.modules.dashboard_mh.tools

import android.app.Activity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.baz.simaht.login.extensions.addFragment
import com.baz.simaht.utils.CoConstants.Companion.ADDING_ACTIONS
import com.baz.simaht.utils.CoConstants.Companion.ADDING_TOOLS
import com.baz.simaht.utils.CoConstants.Companion.EMPLOYEE_FOUND
import com.baz.simaht.utils.CoConstants.Companion.PROCESS_DONE
import com.baz.simaht.utils.CoConstants.Companion.SEARCHING_EMPLOYEE
import com.example.dashboard_mh.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.simaht.dashboard_mh.AssignTool.Tool
import com.simaht.modules.dashboard_mh.tools.done.TransferToolDoneFragment
import com.simaht.modules.dashboard_mh.tools.employeefoundlast.view.EmployeeFoundFragment
import com.simaht.modules.dashboard_mh.tools.searchemployee.SearchEmployeeFragment
import com.simaht.modules.dashboard_mh.tools.toollist.view.AddingToolsFragment
import com.simaht.utils.SelectableItem
import kotlinx.android.synthetic.main.activity_tool_transfer.*
import kotlinx.android.synthetic.main.app_bar_main.*

class ToolTransferActivity : AppCompatActivity(), FragmentCommunication {

    private var counter: Int = 0
    private lateinit var tools: ArrayList<SelectableItem<Tool>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tool_transfer)

        nav_view.setOnNavigationItemSelectedListener(listenerMeu())
        val window = getWindow()
        window.setStatusBarColor(ContextCompat.getColor(this@ToolTransferActivity, R.color.colorAccent))

        nextFragment()
    }

    private fun listenerMeu() = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        return@OnNavigationItemSelectedListener when (item.itemId) {
            R.id.navigationSubMenu -> {

                true
            }
            R.id.navigationHomeDetail -> {
                this@ToolTransferActivity.finish()
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
        goBack()
    }

    override fun onResume() {
        super.onResume()
        window.decorView.apply {
            systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        }
    }

    private fun hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun addTitle(titleBar: String) {
        toolbar.title = titleBar
    }

    fun addToBackStack(fragment: Fragment, backStackTAG: String? = null) {
        if (!backStackTAG.isNullOrEmpty())
            addFragment(fragment, toolsContainer.id, backStackTAG)
        else
            addFragment(fragment, toolsContainer.id)
    }


    private fun goBack() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            counter--
            supportFragmentManager.popBackStack()
        }
    }

    fun proceesDone() {
        supportFragmentManager.popBackStack()
        //TODO add contract fragment
        this@ToolTransferActivity.finish() //FlowCompleted 100%
    }


    override fun addFragment(fragment: Fragment, fragmentTAG: String?) {
        addToBackStack(fragment, fragmentTAG)
    }

    override fun removeFragment() {
        goBack()
    }

    override fun nextFragment(newEmpl: Boolean?, toolsFound: Boolean?) {
        counter++
        when (counter) {
            1 -> {
                addToBackStack(AddingToolsFragment.newInstance(true, this), ADDING_TOOLS)
            }
            2 -> {
                addToBackStack(SearchEmployeeFragment.newInstance(this), SEARCHING_EMPLOYEE)
            }
            3 -> {
                addToBackStack(EmployeeFoundFragment.newInstance(newEmpl ?: false, toolsFound ?: false, tools, this), EMPLOYEE_FOUND)
            }
            4 -> {
                addToBackStack(SearchEmployeeFragment.newInstance(this), ADDING_ACTIONS)
            }
            5 -> {
                addToBackStack(TransferToolDoneFragment.newInstance(true,this), PROCESS_DONE)
            }
            else -> {
                if((R.id.navigationBackDetail as MenuItem).isEnabled)
                    this.finish()
            }
        }

    }

    override fun hideKeyboardEvent(view: View) {
        hideKeyboard(view)
    }

    override fun processDone() {
        (R.id.navigationBackDetail as MenuItem).isEnabled = false
        //DOTO Finish IT
    }

    override fun putScannedTools(tools: ArrayList<SelectableItem<Tool>>) {
        this.tools = tools
    }

}
