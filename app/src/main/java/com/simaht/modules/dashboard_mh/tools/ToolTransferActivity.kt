package com.simaht.modules.dashboard_mh.tools

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.baz.continuidadoperativaletterassignment.alasignature.model.models.ALToolAssignment
import com.baz.continuidadoperativaletterassignment.alasignature.model.models.WrappToolsLA
import com.baz.continuidadoperativaletterassignment.almenu.view.ui.ALetterActivity
import com.baz.simaht.login.extensions.addFragment
import com.baz.simaht.utils.CoConstants.Companion.ADDING_TOOLS
import com.baz.simaht.utils.CoConstants.Companion.EMPLOYEE_FOUND
import com.baz.simaht.utils.CoConstants.Companion.PROCESS_DONE
import com.baz.simaht.utils.CoConstants.Companion.SEARCHING_EMPLOYEE
import com.example.dashboard_mh.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.simaht.dashboard_mh.AssignTool.Tool
import com.simaht.modules.dashboard_mh.tools.done.TransferToolDoneFragment
import com.simaht.modules.dashboard_mh.tools.employeefound.assignment.presenter.ToolAssign
import com.simaht.modules.dashboard_mh.tools.employeefoundlast.view.EmployeeFoundFragment
import com.simaht.modules.dashboard_mh.tools.searchemployee.view.SearchEmployeeFragment
import com.simaht.modules.dashboard_mh.tools.toollist.view.AddingToolsFragment
import com.simaht.modules.login.presenter.Employee
import com.simaht.utils.SelectableItem
import kotlinx.android.synthetic.main.activity_tool_transfer.*
import kotlinx.android.synthetic.main.app_bar_main.*

class ToolTransferActivity : AppCompatActivity(), FragmentCommunication {

    private var counter: Int = 0
    private lateinit var toolsFound: ArrayList<SelectableItem<Tool>>
    private lateinit var toolsToCustody: ArrayList<SelectableItem<Tool>>
    private var firstTools: Boolean = true
    private lateinit var empIdDestination : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tool_transfer)

        nav_view.setOnNavigationItemSelectedListener(listenerMeu())
        val window = getWindow()
        window.setStatusBarColor(ContextCompat.getColor(this@ToolTransferActivity, R.color.colorAccent))

        nextFragment()

        addTitle(resources.getString(R.string.title_tools_flow))
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

        if (supportFragmentManager.fragments.last() is TransferToolDoneFragment)
            processDone()

        if (supportFragmentManager.backStackEntryCount > 0) {
            counter--
            supportFragmentManager.popBackStack()
            if (counter == 0)
                processDone()

            if(counter == 3)
                firstTools = false
        }
    }

    override fun addFragment(fragment: Fragment, fragmentTAG: String?) {
        addToBackStack(fragment, fragmentTAG)
    }

    override fun removeFragment() {
        goBack()
    }

    override fun nextFragment(newEmpl: Boolean?, haveToolsFound: Boolean?, employeeNum: Int?) {
        counter++
        employeeNum?.let { empIdDestination = it.toString() }
        when (counter) {
            1 -> {
                addToBackStack(AddingToolsFragment.newInstance(true, this), ADDING_TOOLS)
            }
            2 -> {
                addToBackStack(SearchEmployeeFragment.newInstance(this), SEARCHING_EMPLOYEE)
            }
            3 -> {
                addToBackStack(EmployeeFoundFragment.newInstance(employeeNum!!,newEmpl ?: false, haveToolsFound ?: false, arrayListOf(), this), EMPLOYEE_FOUND)
            }
            /*4 -> {
                addToBackStack(ToolCustodyFragment.newInstance( toolsToCustody,this), ADDING_ACTIONS)
            }*/
            4 -> {
                addToBackStack(TransferToolDoneFragment.newInstance(true,this), PROCESS_DONE)
            }
            else -> { }
        }

    }

    override fun hideKeyboardEvent(view: View) {
        hideKeyboard(view)
    }

    override fun processDone() {
        this@ToolTransferActivity.finish()
        //initializeLetterAssignment()
    }

    override fun putScannedTools(tools: ArrayList<SelectableItem<Tool>>) {
        if (firstTools) {
            this.toolsFound = tools
            firstTools = false
        } else {
            this.toolsToCustody = tools
        }
    }

    private fun initializeLetterAssignment(){
        val employee = Employee()
        val toolAssign = ToolAssign()
        val employeeName: String = employee.empNombre +" "+ employee.empApellidoMat +" "+ employee.empApellidoPat
        val alToolList : ArrayList<ALToolAssignment> = arrayListOf()

        toolAssign.toolsArray.forEach {
            alToolList.add(ALToolAssignment(it.controlID, it.numSerie, it.numPlaca, it.idCategoria, it.descCategoria, it.idTipo, it.descTipo, it.numSim))
        }

        val intent = Intent(this, ALetterActivity::class.java)
        intent.putExtra("empNameJH",employeeName)
        intent.putExtra("numEmpJH", employee.empID)
        intent.putExtra("numEmpDes", empIdDestination)
        intent.putExtra("tools", WrappToolsLA(alToolList))

        startActivity(intent)
    }

}
