package com.simaht.modules.dashboard_mh.tools.employeefoundlast.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.baz.continuidadoperativaletterassignment.almenu.view.ui.ALetterActivity
import com.example.dashboard_mh.R
import com.simaht.dashboard_mh.AssignTool.Tool
import com.simaht.modules.dashboard_mh.tools.FragmentCommunication
import com.simaht.modules.dashboard_mh.tools.employeefoundlast.contract.IEmployeeFoundContract
import com.simaht.modules.dashboard_mh.tools.employeefoundlast.presenter.EmployeeFoundPresenter
import com.simaht.modules.dashboard_mh.tools.employeefoundlast.view.adapter.ToolListFoundAdapter
import com.simaht.utils.SelectableItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.dialog_notification.view.*
import kotlinx.android.synthetic.main.fragment_employee_found.*


class EmployeeFoundFragment : Fragment(), IEmployeeFoundContract.View {

    private val NEW_EMPLOYE = "IS_NEW_EMPLOYEE"
    private val TOOLS_FOUND = "TOOLS_FOUND"
    private var newEmployee: Boolean? = null
    private var toolsFound: Boolean? = null
    private lateinit var parentView: FragmentCommunication
    private lateinit var toolListFound: ArrayList<SelectableItem<Tool>>
    private lateinit var adapter: ToolListFoundAdapter
    private lateinit var presenter: IEmployeeFoundContract.Presenter
    private lateinit var alertDialog: AlertDialog
    private lateinit var dialogView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            newEmployee = it.getBoolean(NEW_EMPLOYE)
            toolsFound = it.getBoolean(TOOLS_FOUND)
        }

        presenter = EmployeeFoundPresenter(this)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        //TODO We could create a Singleton Emlpoyee to use in every class without transfer all the object

        return inflater.inflate(R.layout.fragment_employee_found, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newEmployee?.let {
            btnAddTool.isEnabled = it
        }

        rvContentTool.layoutManager = LinearLayoutManager(activity)
        rvContentTool.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))

        adapter = ToolListFoundAdapter(toolListFound, true) {selected, item ->

            btnContinue.isEnabled = adapter.haveCustody()

            if (selected)
                presenter.addToolToCustody(item)
            else
                presenter.removeFromCystody(item)
        }

        rvContentTool.adapter = adapter

        Picasso.get()
                .load("//url.com.mx")
                .placeholder(R.drawable.avatar_employee)
                .error(R.drawable.avatar_employee)
                .into(employeeFace)

        btnContinue.setOnClickListener {
            //parentView.putScannedTools(adapter.listToCustody())

            notifyAction()


            //parentView.nextFragment()
        }
        presenter.employee()
        presenter.searchTools()

    }

    companion object {
        @JvmStatic
        fun newInstance(isNewEmployee: Boolean, toolsFound: Boolean, tools: ArrayList<SelectableItem<Tool>>, communication: FragmentCommunication) =
                EmployeeFoundFragment().apply {
                    toolListFound = tools
                    parentView = communication
                    arguments = Bundle().apply {
                        putBoolean(NEW_EMPLOYE, isNewEmployee)
                        putBoolean(TOOLS_FOUND, toolsFound)
                    }
                }
    }

    override fun putList() {

    }

    override fun showLoader() {

    }

    override fun hideLoader() {

    }

    override fun setEmployee() {
        val number = 46244
        employeeAbstract.text = "${number} - GCR"
        employeeName.text = "Banco Azteca Test JH"

    }

    override fun addItemFound(toolsFound: List<SelectableItem<Tool>>) {
        if (toolsFound.isNotEmpty()) {
            adapter.addToolsFound(toolsFound)
            notifyHaveTools()
        }
    }

    override fun custodyDone() {
        parentView.nextFragment()
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
        adapter.removeTemporalTools()
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
            goBack()
        }
    }

}
