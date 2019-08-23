package com.simaht.modules.dashboard_mh.tools.employeefoundlast.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.baz.simaht.login.extensions.toast
import com.example.dashboard_mh.BuildConfig
import com.example.dashboard_mh.R
import com.simaht.dashboard_mh.AssignTool.Tool
import com.simaht.modules.dashboard_mh.tools.FragmentCommunication
import com.simaht.modules.dashboard_mh.tools.employeefoundlast.contract.IEmployeeFoundContract
import com.simaht.modules.dashboard_mh.tools.employeefoundlast.presenter.EmployeeFoundPresenter
import com.simaht.modules.dashboard_mh.tools.employeefoundlast.view.adapter.DeviceListFoundAdapter
import com.simaht.modules.dashboard_mh.tools.employeefoundlast.view.adapter.ToolListFoundAdapter
import com.simaht.modules.model.ActivoFijo
import com.simaht.utils.SelectableItem
import com.simaht.utils.Utils
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.dialog_notification.view.*
import kotlinx.android.synthetic.main.fragment_employee_found.*


class EmployeeFoundFragment : Fragment(), IEmployeeFoundContract.View {

    private val EMPLOYE_NUM = "EMPLOYEE_NUMBER"
    private val NEW_EMPLOYE = "IS_NEW_EMPLOYEE"
    private val TOOLS_FOUND = "TOOLS_FOUND"
    private var employeeNumber: Int = -1
    private var newEmployee: Boolean? = null
    private var toolsFound: Boolean? = null
    private lateinit var parentView: FragmentCommunication
    private lateinit var toolListFound: ArrayList<SelectableItem<Tool>>
    private lateinit var deviceListFound: ArrayList<SelectableItem<ActivoFijo>>
    private lateinit var adapterTools: ToolListFoundAdapter
    private lateinit var adapterDevices: DeviceListFoundAdapter
    private lateinit var presenter: IEmployeeFoundContract.Presenter
    private lateinit var alertDialog: AlertDialog
    private lateinit var dialogView: View
    private var isDebug = false
    private var custodyFlow = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            employeeNumber = it.getInt(EMPLOYE_NUM, -1)
            newEmployee = it.getBoolean(NEW_EMPLOYE)
            toolsFound = it.getBoolean(TOOLS_FOUND)
        }

        isDebug = BuildConfig.DEBUG
        presenter = EmployeeFoundPresenter(this)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        //TODO We could create a Singleton Emlpoyee to use in every class without transfer all the object

        return inflater.inflate(R.layout.fragment_employee_found, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newEmployee?.let {//FIXME im not sut until which moment is a new employee
            btnAddTool.isEnabled = it
        }

        rvContentTool.layoutManager = LinearLayoutManager(activity)
        rvContentTool.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))


        if (!isDebug) { //FIXME Repare tis negation
            adapterTools = ToolListFoundAdapter(arrayListOf(), true) { selected, item ->
                btnContinue.isEnabled = adapterTools.haveCustody()
                if (selected)
                    presenter.addToolToCustodyTest(item)
                else
                    presenter.removeFromCystodyTest(item)
            }
            rvContentTool.adapter = adapterTools
        } else {
            adapterDevices = DeviceListFoundAdapter(arrayListOf(), true) { selected, item ->
                btnContinue.isEnabled = adapterDevices.haveCustody()
                if (selected)
                    presenter.addToolToCustody(item)
                else
                    presenter.removeFromCystody(item)
            }
            rvContentTool.adapter = adapterDevices
        }


        Picasso.get()
                .load("//url.com.mx")
                .placeholder(R.drawable.avatar_employee)
                .error(R.drawable.avatar_employee)
                .into(employeeFace)

        btnContinue.setOnClickListener {
            //parentView.putScannedTools(adapter.listToCustody())
            if (custodyFlow)
                notifyAction()
            else
                parentView.processDone()

            //parentView.nextFragment()
        }
        //presenter.employee()
        presenter.searchTools(employeeNumber)

    }

    companion object {
        @JvmStatic
        fun newInstance(employee: Int, isNewEmployee: Boolean, toolsFound: Boolean, tools: ArrayList<SelectableItem<Tool>>, communication: FragmentCommunication) =
                EmployeeFoundFragment().apply {
                    toolListFound = tools
                    parentView = communication
                    arguments = Bundle().apply {
                        putInt(EMPLOYE_NUM, employee)
                        putBoolean(NEW_EMPLOYE, isNewEmployee)
                        putBoolean(TOOLS_FOUND, toolsFound)
                    }
                }
    }

    override fun putList() {

    }

    override fun showLoader() {
        Utils.progressDialogShow(context!!)
    }

    override fun hideLoader() {
        Utils.progressDialogDismiss()
    }

    override fun setEmployee(haveCustody: Boolean, name: String) {
        employeeAbstract.text = "${employeeNumber} - GCR"//FIXME set It from resourses using %s
        employeeName.text = name
        if (!haveCustody){
            btnContinue.isEnabled = true
            custodyFlow = haveCustody
        }
    }

    override fun addItemFound(toolsFoundTest: ArrayList<SelectableItem<Tool>>?, devicesFound: ArrayList<SelectableItem<ActivoFijo>>?) {

        /*if (isDebug && toolsFound != null) { //
            toolListFound.let {

                if (it.isNotEmpty()) {
                    adapterTools.addToolsFound(it)
                    notifyHaveTools()
                } else {
                    //TODO send  message
                    adapterTools.addToolsFound(emptyList())
                    notifyHaveTools()
                }
            }
        } else {*/

        devicesFound?.let {
            adapterDevices = DeviceListFoundAdapter(it, true) { selected, item ->
                btnContinue.isEnabled = adapterDevices.haveCustody() //TODO change by have a selection
                if (selected)
                    presenter.addToolToCustody(item)
                else
                    presenter.removeFromCystody(item)
            }
            rvContentTool.adapter = adapterDevices

            if (it.isNotEmpty()) {
                notifyHaveTools()
            } else {
                adapterDevices.addToolsFound(emptyList())
                notifyHaveTools()
            }
        }
        //}
    }

    override fun custodyDone() {
        parentView.nextFragment()
    }

    override fun wrongCustody() {
        showMessage("Un error inesperado ha ocurrido, intente nuevamente")
        adapterDevices.errorCustody()
    }

    override fun showMessage(msg: String) {
        toast(msg)
    }

    override fun inespectedError() {
        parentView.removeFragment()
    }


    private fun notifyHaveTools() {

        val dialogBuilder = AlertDialog.Builder(activity!!)
        dialogView = layoutInflater.inflate(R.layout.dialog_notification, null)

        //CUSTODY -> {
        dialogView.ivImageAlert.visibility = View.GONE
        dialogView.tvNormalDialog.text = String.format(resources.getString(R.string.msg_employee_with_tools))
        //}
        dialogBuilder.setView(dialogView)
                .setCancelable(false)
        alertDialog = dialogBuilder.create()
        alertDialog.show()

        /*val builder = AlertDialog.Builder(context, R.style.DialogStyle)//Context is activity context
        viewAlert = activity!!.layoutInflater.inflate(R.layout.dialog_notification, null, false)*/
        /*
        builder.setView(viewAlert)
                .setCancelable(false)
                .setMessage("")
                .setTitle(null)
                .setMessage(null)
        val lp = /*LinearLayout.LayoutParams(500,
                LinearLayout.LayoutParams.MATCH_PARENT)*/
        ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT,ConstraintLayout.LayoutParams.WRAP_CONTENT)
        viewAlert.layoutParams = lp
        builder.setView(viewAlert)
        dialog = builder.create()
        setDimentionsDialog()*/
        //dialog.show()

        dialogView.btnDialogOK.setOnClickListener {
            alertDialog.dismiss()
        }
        dialogView.btnDialogCancel.setOnClickListener {
            alertDialog.dismiss()
            goBack()
        }
    }

    private fun goBack() {
        if (!isDebug)//FIXME NOT DEBUG
            adapterTools.removeTemporalTools()
        else
            adapterDevices.removeTemporalTools()
        parentView.removeFragment()
    }


    private fun notifyAction() {

        val dialogBuilder = AlertDialog.Builder(activity!!)
        dialogView = layoutInflater.inflate(R.layout.dialog_notification, null)
        dialogView.ivImageAlert.visibility = View.VISIBLE
        dialogView.tvNormalDialog.text = String.format(resources.getString(R.string.msg_custody_tool), employeeName.text.toString())
        dialogBuilder.setView(dialogView)
                .setCancelable(false)
        alertDialog = dialogBuilder.create()
        alertDialog.show()

        /*val builder = AlertDialog.Builder(context, R.style.DialogStyle)//Context is activity context
        viewAlert = activity!!.layoutInflater.inflate(R.layout.dialog_notification, null, false)*/
        /*
        builder.setView(viewAlert)
                .setCancelable(false)
                .setMessage("")
                .setTitle(null)
                .setMessage(null)
        val lp = /*LinearLayout.LayoutParams(500,
                LinearLayout.LayoutParams.MATCH_PARENT)*/
        ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT,ConstraintLayout.LayoutParams.WRAP_CONTENT)
        viewAlert.layoutParams = lp
        builder.setView(viewAlert)
        dialog = builder.create()
        setDimentionsDialog()*/
        //dialog.show()

        dialogView.btnDialogOK.setOnClickListener {
            alertDialog.dismiss()
            presenter.applyCustody()
        }
        dialogView.btnDialogCancel.setOnClickListener {
            alertDialog.dismiss()
            if (!custodyFlow)
                goBack()
        }
    }

}
